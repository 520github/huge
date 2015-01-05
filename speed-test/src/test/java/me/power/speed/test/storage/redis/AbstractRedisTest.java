package me.power.speed.test.storage.redis;

import java.util.List;

import com.gameanalytics.bitmap.Bitmap;
import com.gameanalytics.bitmap.impl.ConciseBitmapImpl;

import me.power.speed.test.AbstractTest;
import me.power.speed.test.ConsumerTime;
import me.power.speed.test.ConsumerTime.ConsumerTimeHandle;
import me.power.speed.test.storage.bitmap.BitmapUtil;

public class AbstractRedisTest extends AbstractTest {
	protected static AbstractRedisTest redisTest = new AbstractRedisTest();
	protected static int count =0;
	
	protected RedisUtil getRedisUtil() {
		return new RedisUtil();
	}
	
	protected void handleWithConsumerTimeAndRedisMemory(ConsumerTimeHandle handle, int cycleNum) {
		long beforeUsedMemory = this.getRedisUsedMemory();
		ConsumerTime ct = new ConsumerTime();
		handle.handle();
		ct.endConsumeTime();
		long afterUsedMemory = this.getRedisUsedMemory();
		long usedMemory = afterUsedMemory - beforeUsedMemory;
		this.print("used total memory:" + usedMemory, true);
		this.print("per used memory:" + usedMemory/cycleNum, true);
		this.print("------------------------", true);
	}
	
	protected long getRedisUsedMemory() {
		return RedisUtil.getRedisCurrentUsedMemory();
	}
	
	protected void setValueToRedis(String key, byte[] datas) {
	    RedisUtil.setValueToKey(key, datas);
	}
	
	protected byte[] getBytesDataFromRedis(String key) {
		return RedisUtil.getValueFromKey(key);
	}
	
	protected int[] getArraysByList(List<Integer> offsets) {
		int arrays[] = new int[offsets.size()];
		int count =0;
		for(Integer offset: offsets) {
			arrays[count] = offset;
			count++;
		}
		return arrays;
	}
	
	protected void handleSetOffsetsToBitmap(Bitmap bitmap,List<Integer> offsets) {
		this.handleSetOffsetsToBitmap(bitmap, this.getArraysByList(offsets));
	}
	
	protected void handleSetOffsetsToBitmap(final Bitmap bitmap, final int offsets[]) {
		this.handleWithConsumerTime(new ConsumerTimeHandle() {
			public void handle() {
				for(int offset : offsets) {
					bitmap.set(offset);
				}
			}
		});
	}
	
	protected void handleBitmapOrOperate(final Bitmap fBitmap, final Bitmap sBitmap) {
		this.print("first bitmap:" + fBitmap.cardinary(), true);
		this.print("second bitmap:" + sBitmap.cardinary(), true);
		
		this.handleWithConsumerTime(new ConsumerTimeHandle() {
			public void handle() {
				Bitmap result = fBitmap.or(sBitmap);
				ConsumerTime ct = new ConsumerTime();
				redisTest.print(result.cardinary(), true);
				ct.endConsumeTime();
			}
		});
	}
	
	protected void handleMulitBitmapOrOperate(final Bitmap... bitmaps) {
		int count =1;
		for(Bitmap bitmap: bitmaps) {
			this.print("bitmap " + count + " count:" + bitmap.cardinary(), true);
			count++;
		}
		this.handleWithConsumerTime(new ConsumerTimeHandle() {
			public void handle() {
				Bitmap result = null;
				int count =1;
				for(Bitmap bitmap: bitmaps) {
					if(count ==1) {
						result = bitmap;
					}
					else {
						result = result.or(bitmap);
					}
					count++;
				}
				ConsumerTime ct = new ConsumerTime();
				redisTest.print("result bitmap count:" + result.cardinary(), true);
				ct.endConsumeTime();
			}
		});
	}
	
	protected void handleMulitBitmapOrAndOperate(final Bitmap fbitmaps[], final Bitmap sbitmaps[]) {
		int count =1;
		for(Bitmap bitmap: fbitmaps) {
			this.print("fbitmap " + count + " count:" + bitmap.cardinary(), true);
			count++;
		}
		count =1;
		for(Bitmap bitmap: sbitmaps) {
			this.print("sbitmap " + count + " count:" + bitmap.cardinary(), true);
			count++;
		}
		this.handleWithConsumerTime(new ConsumerTimeHandle() {
			public void handle() {
				Bitmap fresult = null;
				int count =1;
				for(Bitmap bitmap: fbitmaps) {
					if(count ==1) {
						fresult = bitmap;
					}
					else {
						fresult = fresult.or(bitmap);
					}
					count++;
				}
				redisTest.print("first bitmap count:" + fresult.cardinary(), true);
				
				Bitmap sresult = null;
				int scount = 1;
				for(Bitmap bitmap: sbitmaps) {
					if(scount ==1) {
						sresult = bitmap;
					}
					else {
						sresult = sresult.or(bitmap);
					}
					scount++;
				}
				redisTest.print("second bitmap count:" + sresult.cardinary(), true);
				
				
				Bitmap result = fresult.and(sresult);
				ConsumerTime ct = new ConsumerTime();
				redisTest.print("result bitmap count:" + result.cardinary(), true);
				ct.endConsumeTime();
			}
		});
	}
	
	protected void handleBitmapAndOperate(final Bitmap fBitmap, final Bitmap sBitmap) {
		this.print("first bitmap:" + fBitmap.cardinary(), true);
		this.print("second bitmap:" + sBitmap.cardinary(), true);
		
		this.handleWithConsumerTime(new ConsumerTimeHandle() {
			public void handle() {
				Bitmap result = fBitmap.and(sBitmap);
				ConsumerTime ct = new ConsumerTime();
				redisTest.print("result bitmap count:" + result.cardinary(), true);
				ct.endConsumeTime();
			}
		});
	}
	
	protected void handleMulitBitmapAndOperate(final Bitmap... bitmaps) {
		int count =1;
		for(Bitmap bitmap: bitmaps) {
			this.print("bitmap " + count + " count:" + bitmap.cardinary(), true);
			count++;
		}
		this.handleWithConsumerTime(new ConsumerTimeHandle() {
			public void handle() {
				Bitmap result = null;
				int count =1;
				for(Bitmap bitmap: bitmaps) {
					if(count ==1) {
						result = bitmap;
					}
					else {
						result = result.and(bitmap);
					}
					count++;
				}
				ConsumerTime ct = new ConsumerTime();
				redisTest.print("result bitmap count:" + result.cardinary(), true);
				ct.endConsumeTime();
			}
		});
	}
	
	protected void handleBitmapToRedis(final Bitmap bitmap,final String key, final List<Integer> offsets) {
		this.handleBitmapToRedis(bitmap, key, this.getArraysByList(offsets));
	}
	
	protected void handleBitmapToRedis(final Bitmap bitmap,final String key, final int offsets[]) {
		this.handleWithConsumerTime(new ConsumerTimeHandle() {
			public void handle() {
				for(int offset : offsets) {
					bitmap.set(offset);
				}
				ConsumerTime ct = new ConsumerTime();
				redisTest.setValueToRedis(key, redisTest.getBytesFromBitmap(bitmap));
				ct.endConsumeTime();
			}
		});
		this.print(key, true);
		this.print(this.getBitmapCount(key), true);
	}
	
	protected byte[] getBytesFromBitmap(Bitmap bitmap) {
		ConsumerTime cs = new ConsumerTime();
		byte datas[]= BitmapUtil.bitmapToByteArray(bitmap);
		cs.endConsumeTime();
		this.print("bitmap bytes length:" + datas.length);
		return datas;
	}
	
	protected Bitmap getBitmapFromRedis(String key) {
		byte datas[] = RedisUtil.getValueFromKey(key);
		return this.getBitmapFromBytes(datas);
	}
	
	protected Bitmap getBitmapFromBytes(byte[] datas) {
		if(datas == null) {
			this.print("find datas byte is empty and return new bitmap.");
			return this.newBitmap();
		}
		return BitmapUtil.byteArrayToBitmap(datas);
	}
	
	protected int getBitmapCount(String key) {
		byte datas[] = RedisUtil.getValueFromKey(key);
		return this.getBitmapCount(datas);
	}
	
	protected int getBitmapCount(byte datas[]) {
		this.print("bytes length:" + datas.length, true);
		Bitmap bitmap = this.getBitmapFromBytes(datas);
		return bitmap.cardinary();
	}
	
	protected Bitmap newBitmap() {
		return new ConciseBitmapImpl();
	}
	
	public void setValuesToPf(String key, String... value) {
		RedisUtil.setValuesToPf(key, value);
	}
	
	public void getAndPrintPfCount(String key) {
		ConsumerTime ct = new ConsumerTime();
		long result = RedisUtil.getCountFromPf(key);
		ct.endConsumeTime();
		this.print(key + "->" +result,true);
	}
	
	public void getAndPrintPfCount(String key, long expect) {
		ConsumerTime ct = new ConsumerTime();
		long result = RedisUtil.getCountFromPf(key);
		ct.endConsumeTime();
		if(expect != result) {
			this.print(key + ":" +result + " is not equal " + expect,true);
			count++;
		}	
	}
	
	public long getPfCount(String key) {
		return RedisUtil.getCountFromPf(key);
	}
}
