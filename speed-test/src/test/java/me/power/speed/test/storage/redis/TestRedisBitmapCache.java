package me.power.speed.test.storage.redis;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.gameanalytics.bitmap.Bitmap;
import com.gameanalytics.bitmap.impl.ConciseBitmapImpl;

public class TestRedisBitmapCache extends AbstractRedisTest {
	private Map<String, Bitmap> bitmapCache = new HashMap<String, Bitmap>();
	private String key = "bitmap:cache:1";
	
	@Test
	public void testRedisBitmapCache() {
		int offsets[] = this.getIncreamIntArrays(10, 100, 2);
		this.setOffsetsToBitmap(key, offsets);
		this.print(this.getBitmapCountFromCache(key), true);
		
		this.setValueToRedis(key, this.getBytesFromBitmapCache(key));
		this.print(this.getBitmapCount(key), true);
	}
	
	private void setOffsetsToBitmap(String key, int offsets[]) {
		for(int offset: offsets) {
			this.setOffsetToBitmap(key, offset);
		}
	}
	
	private void setOffsetToBitmap(String key, int offset) {
		if(bitmapCache.containsKey(key)) {
			bitmapCache.get(key).set(offset);
			return;
		}
		
		Bitmap bitmap = new ConciseBitmapImpl();
		bitmap.set(offset);
		bitmapCache.put(key, bitmap);
	}
	
	private byte[] getBytesFromBitmapCache(String key) {
		return this.getBytesFromBitmap(bitmapCache.get(key));
	}
	
	private int getBitmapCountFromCache(String key) {
		return bitmapCache.get(key).cardinary();
	}
}
