package me.power.speed.storage.redis.bitmap;

import redis.clients.jedis.Jedis;
import me.power.speed.AbstractBaseTest;
import me.power.speed.ConsumerTime.ConsumerTimeHandle;
import me.power.speed.storage.redis.RedisUtil;

public abstract class AbstractRedisBitmapTest extends AbstractBaseTest {
	protected AbstractBaseTest baseTest = new AbstractBaseTest();
	protected RedisBitmap redisBitmap;
	
	protected void testSetBitmapOffset(final String key, final int offsets[]) {
		long beforeValue = RedisUtil.getRedisCurrentUsedMemory();
		final Jedis jedis = this.getJedis();
		this.handleWithConsumerTime(new ConsumerTimeHandle() {
			public void handle() {
				for(int offset: offsets) {
					try {
						redisBitmap.setBitmapOffset(jedis, key, offset);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		this.print("get bitmap count....", true);
		this.testGetBitmapCount(key);
		this.returnJedis(jedis);
		
		long afterValue = RedisUtil.getRedisCurrentUsedMemory();
		long usedValue = afterValue - beforeValue;
		this.print("beforeValue:"+beforeValue+",afterValue:"+afterValue+",used memory " + usedValue, true);
	}
	
	protected void testGetBitmapCount(final String key) {
		final Jedis jedis = this.getJedis();
		this.handleWithConsumerTime(new ConsumerTimeHandle() {
			public void handle() {
				try {
					int result = redisBitmap.getBitmapCount(jedis, key);
					baseTest.print(String.valueOf(result));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		this.returnJedis(jedis);
	}
	
	protected Jedis getJedis() {
		return RedisUtil.getRedisConnection();
	}
	
	protected void returnJedis(Jedis jedis) {
		RedisUtil.returnRedisConnector(jedis);
	}
}
