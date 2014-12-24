package me.power.speed.test.storage.redis;

import org.junit.Test;

import com.gameanalytics.bitmap.Bitmap;
import com.gameanalytics.bitmap.impl.ConciseBitmapImpl;
import me.power.speed.test.ConsumerTime.ConsumerTimeHandle;

public class TestRedisBitmap extends AbstractRedisTest {
	private Bitmap bitmap = new ConciseBitmapImpl();
	private String key = "bitkey:20141224:1";
	
	@Test
	public void setBitmapBytesToRedis() {
		bitmap.set(100);
		bitmap.set(206);
		bitmap.set(120000000);
		RedisUtil.setValueToKey(key, this.getBytesFromBitmap());
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
	
	private byte[] getBytesFromBitmap() {
		return this.getBytesFromBitmap(bitmap);
	}
	
}
