/**
 * 
 */
package me.power.speed.storage.redis.bitmap;

import java.util.HashMap;
import java.util.Map;

import me.power.speed.storage.redis.bitmap.impl.RedisCompressBitmapImpl;
import me.power.speed.storage.redis.bitmap.util.CompressBitmapUtil;

import org.junit.Before;
import org.junit.Test;

import com.gameanalytics.bitmap.Bitmap;

/**
 * @author xuehui.miao
 *
 */
public class TestRedisCompressBitmapImpl extends AbstractRedisBitmapTest {
	private String key = "bitmap:compress:20141230:1";
	@Before
	public void before() {
		this.redisBitmap = new RedisCompressBitmapImpl();
	}
	
	@Test
	public void testSetBitmapOffset() {
		int offsets[] = this.getIncreamIntArrays(10001, 1, 100);
		this.testSetBitmapOffset(key, offsets);
	}
	
	@Test
	public void testGetBitmapCount() {
		this.testGetBitmapCount(key);
	}
	
	@Test
	public void testGetBitmapFromFileName() {
		String fileName = "C:\\xuehui\\50-temp\\10-datafilter-data\\20-thread\\";
		fileName = fileName + "a.bitmap";
		try {
			Bitmap bitmap = CompressBitmapUtil.getBitmapFromFileName(fileName);
			int i =0;
			Map<Integer, Bitmap> cache = new HashMap<Integer, Bitmap>();
			while(true) {
				if(i < 0) {
					System.out.println("i:" + i);
					break;
				}
				if(i<100) {
					cache.put(i, bitmap);
					System.out.println("i:" + i);
					this.sleep(1000);
				}
				else {
					//this.sleep(20000);
				}
				i++;
			}
		} catch (Exception e) {
			this.fail(e);
		}
	}
}
