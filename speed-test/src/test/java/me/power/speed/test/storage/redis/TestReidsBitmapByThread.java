package me.power.speed.test.storage.redis;

import me.power.speed.test.ConsumerTime;

import org.junit.Test;

import com.gameanalytics.bitmap.Bitmap;

public class TestReidsBitmapByThread extends AbstractRedisTest {
	AbstractRedisTest testObject = new AbstractRedisTest();
	private String title = "test redis bitmap by thread";
	private int start = 0;
	private int count = 10000;
	private String key = "thread:bitmap:20141224:4";
	
	//单层循环
	@Test
	public void testSigleCycleRedisBitmapByThread() {
		this.setMeasurements(1).setThreads(1).setSerialTimes(1).setTitle(title);
		this.runDefaultByMilitThread(new Runnable() {
			public void run() {
				ConsumerTime ct = new ConsumerTime();
				start = 120000000;
				count = 10000;
				for(int i=start; i<start+count; i++) {
					Bitmap bitmap = testObject.getBitmapFromBytes(testObject.getBytesDataFromRedis(key));
					bitmap.set(i);
					RedisUtil.setValueToKey(key, testObject.getBytesFromBitmap(bitmap));
				}
				ct.endConsumeTime();
			}
		});
		
		int count = this.getBitmapCount(key);
		this.print(count, true);
	}
	
	@Test
	public void testSigleCycleRedisBitmap() {
		ConsumerTime ct = new ConsumerTime();
		start = 120000000;
		count = 10000;
		for(int i=start; i<start+count; i++) {
			Bitmap bitmap = testObject.getBitmapFromBytes(testObject.getBytesDataFromRedis(key));
			bitmap.set(i);
			RedisUtil.setValueToKey(key, testObject.getBytesFromBitmap(bitmap));
		}
		ct.endConsumeTime();
	}
	
	@Test
	public void testGetBitmapCount() {
		this.isPrint = true;
		int count = this.getBitmapCount(key);
		this.print(count, true);
	}
}
