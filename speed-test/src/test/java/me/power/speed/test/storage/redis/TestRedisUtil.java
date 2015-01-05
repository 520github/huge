package me.power.speed.test.storage.redis;

import org.junit.Test;

public class TestRedisUtil extends AbstractRedisTest {
	
	@Test
	public void testRedisEval() {
		String result = RedisUtil.eval("info");
		this.print(result, true);
	}
	
	@Test
	public void testRedisInfo() {
		String info = RedisUtil.info();
		this.print(info, true);
	}
	
	@Test
	public void testGetRedisCurrentUsedMemory() {
		long result = RedisUtil.getRedisCurrentUsedMemory();
		this.print(result);
	}
}
