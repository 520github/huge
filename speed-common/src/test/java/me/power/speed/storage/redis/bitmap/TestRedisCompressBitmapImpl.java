/**
 * 
 */
package me.power.speed.storage.redis.bitmap;

import me.power.speed.storage.redis.bitmap.impl.RedisCompressBitmapImpl;

import org.junit.Before;
import org.junit.Test;

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
}
