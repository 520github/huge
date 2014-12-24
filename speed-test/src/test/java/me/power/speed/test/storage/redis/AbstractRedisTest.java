package me.power.speed.test.storage.redis;

import com.gameanalytics.bitmap.Bitmap;
import com.gameanalytics.bitmap.impl.ConciseBitmapImpl;

import me.power.speed.test.AbstractTest;
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
	
	protected byte[] getBytesFromBitmap(Bitmap bitmap) {
		byte datas[]= BitmapUtil.bitmapToByteArray(bitmap);
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
		this.print("bytes length:" + datas.length);
		Bitmap bitmap = this.getBitmapFromBytes(datas);
		return bitmap.cardinary();
	}
	
	protected Bitmap newBitmap() {
		return new ConciseBitmapImpl();
	}
}
