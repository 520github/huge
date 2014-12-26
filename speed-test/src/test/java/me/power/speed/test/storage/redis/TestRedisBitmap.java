package me.power.speed.test.storage.redis;

import java.util.List;
import org.junit.Test;
import com.gameanalytics.bitmap.Bitmap;
import com.gameanalytics.bitmap.impl.ConciseBitmapImpl;

import me.power.speed.test.ConsumerTime;
import me.power.speed.test.ConsumerTime.ConsumerTimeHandle;

public class TestRedisBitmap extends AbstractRedisTest {
	private Bitmap bitmap = new ConciseBitmapImpl();
	private String key = "bitkey:20141224:99";
	private int start = 0;
	private int count = 10000;
	
	@Test
	public void setBitmapBytesToRedis() {
		this.handleWithConsumerTime(new ConsumerTimeHandle() {
			public void handle() {
				bitmap.set(7349020);
				RedisUtil.setValueToKey(key, redisTest.getBytesFromBitmap(bitmap));
			}
		});
		this.getBitmapCount(key);
		redisTest.print(bitmap.cardinary(), true);
	}
	
	@Test
	public void setNewBitmapBytesToRedis() {
		this.handleWithConsumerTime(new ConsumerTimeHandle() {
			public void handle() {
				bitmap.set(100002993);
				redisTest.setValueToRedis(key, redisTest.getBytesFromBitmap(bitmap));
			}
		});
	}
	
	@Test
	public void setCycleNewBitmapBytesToRedis() {
		this.handleWithConsumerTime(new ConsumerTimeHandle() {
			public void handle() {
				start = 120000000;
				count = 100000000;
				for(int i = start;i<start+count;i++) {
					bitmap.set(i);
				}
				ConsumerTime ct = new ConsumerTime();
				redisTest.setValueToRedis(key, redisTest.getBytesFromBitmap(bitmap));
				ct.endConsumeTime();
			}
		});
	}
	
	@Test
	public void testSetOffset() {
		int randomList[] = this.getRandomIntArrays(10, 10000000);
		List<Integer> offsets = this.getSortOffsetListByOffsetArray(randomList);
//		Set<Integer> offsets = getOffsetSets();
		for(Integer offset: offsets) {
			this.print(String.valueOf(offset), true);
		}
	}
	
	@Test
	public void testBitmapLengthWithOffsetSort() {
		key = "bitkey:20141224:72";
		int offsets[] = this.getRandomRangeIntArrays(100000, 3000000);
		Bitmap bitmap1 = new ConciseBitmapImpl();
		this.handleBitmapToRedis(bitmap1, key, offsets);
		
		key = "bitkey:20141224:73";
		List<Integer> sortList = this.getSortOffsetListByOffsetArray(offsets);
		Bitmap bitmap2 = new ConciseBitmapImpl();
		this.handleBitmapToRedis(bitmap2, key, sortList);
	}
	
	@Test
	public void setCycleNewAndOldBitmapBtyesToRedis() {
		key = "bitkey:20141226:1";
		boolean isSort = false;
		int offsets[] = this.getIncreamIntArrays(100000, 3000000,  1000);
		//int offsets[] = this.getRandomIntArrays(100000, 10000000);
		//int offsets[] = this.getRandomRangeIntArrays(100000, 3000000);
		List<Integer> sortList = this.getSortOffsetListByOffsetArray(offsets);
		if(isSort) {
			this.handleBitmapToRedis(bitmap, key, sortList);
		}
		else {
			this.handleBitmapToRedis(bitmap, key, offsets);
		}
		
		for(int i=0; i<3;i++) {
			ConsumerTime ct = new ConsumerTime();
			Bitmap oldBitmap = this.getBitmapFromRedis(key);
			ct.endConsumeTime();
			this.print("get bitmap from redis by key " + key, true);
			
			if(isSort) {
				this.handleBitmapToRedis(oldBitmap, key, offsets);
			}else{
				this.handleBitmapToRedis(oldBitmap, key, offsets);
			}
		}
	}
	
	
	@Test
	public void setCycleRandomNewBitmapBytesToRedis() {
		key = "bitkey:20141224:45";
		//final List<Integer> randomList = this.getRandomIntegerList(100000, 10000000);
		final int randomList[] = this.getRandomIntArrays(100000, 10000000);
		//final List<Integer> randomList = this.getSortOffsetListByOffsetArray(arrays);
		//final int randomList[] = this.getIncreamIntArrays(100000, 222222,  10000);
		//final int randomList[] = this.getDecreamIntArrays(100000, 88,  10000);
		//final int randomList[] = this.getTwoIncreamIntArrays(50000, 99,  10000);
		//final int randomList[] = this.getSameIntArrays(100000, 100000000);
		this.handleWithConsumerTime(new ConsumerTimeHandle() {
			public void handle() {
//				start = 120000000;
//				count = 100000;
//				for(int i = start;i<start+count;i++) {
//					double d = Math.random();
//					int result = (int)(d*d*d*count);
//					bitmap.set(result);
//				}
				for(int num: randomList) {
					bitmap.set(num);
				}
				ConsumerTime ct = new ConsumerTime();
				redisTest.setValueToRedis(key, redisTest.getBytesFromBitmap(bitmap));
				ct.endConsumeTime();
			}
		});
		this.print(key, true);
		this.getBitmapCountFromRedis();
	}
	
	@Test
	public void setExistBitmapBytesToRedis() {
		this.handleWithConsumerTime(new ConsumerTimeHandle() {
			public void handle() {
				Bitmap bitmap = redisTest.getBitmapFromRedis(key);
				bitmap.set(30990);
				redisTest.setValueToRedis(key, redisTest.getBytesFromBitmap(bitmap));
			}
		});
	}
	
	@Test
	public void setExistBitmapByteToRedis() {
		Bitmap bitmap = this.getBitmapFromRedis(key);
		this.setMuliteOffsetToBitmap(bitmap, 5000000, 5000000);
		RedisUtil.setValueToKey(key, this.getBytesFromBitmap(bitmap));
	}
	
	@Test
	public void getBitmapCountFromRedis() {
		byte datas[] = RedisUtil.getValueFromKey(key);
		int count = this.getBitmapCount(datas);
		this.print(String.valueOf(count), true);
	}
	
	protected void setMuliteOffsetToBitmap(int startOffset, int offsetCount) {
		this.setMuliteOffsetToBitmap(bitmap, startOffset, offsetCount);
	}
	
	private void setMuliteOffsetToBitmap(Bitmap bitmap, int startOffset, int offsetCount) {
		for(int i=startOffset;i<startOffset+offsetCount;i++) {
			bitmap.set(i);
		}
	}
}
