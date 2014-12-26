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
	
	protected RedisUtil getRedisUtil() {
		return new RedisUtil();
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
}
