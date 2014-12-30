package me.power.speed.storage.redis.bitmap;

import me.power.speed.storage.redis.bitmap.impl.RedisCompressBitmapImpl;
import me.power.speed.storage.redis.bitmap.impl.RedisInnerBitmapImpl;

import org.junit.Before;
import org.junit.Test;

public class TestRedisInnerBitmapImpl extends AbstractRedisBitmapTest {
	private String key = "bitmap:inner:20141230:1";
	@Before
	public void before() {
		this.redisBitmap = new RedisInnerBitmapImpl();
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
