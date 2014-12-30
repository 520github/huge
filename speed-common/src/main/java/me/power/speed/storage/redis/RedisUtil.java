package me.power.speed.storage.redis;

import redis.clients.jedis.Jedis;

public class RedisUtil {
	
	public static void setBitmap(String key, long offset) {
		Jedis jedis = null;
		try {
			jedis = getRedisConnection();
			jedis.setbit(key, offset, true);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			returnRedisConnector(jedis);
		}
	}
	
	public static void setValueToKey(String key, byte[] value) {
		Jedis jedis = null;
		try {
			jedis = getRedisConnection();
			jedis.set(getBtyesKey(key), value);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			returnRedisConnector(jedis);
		}
	}
	
	public static byte[] getValueFromKey(String key) {
		Jedis jedis = null;
		try {
			jedis = getRedisConnection();
			return jedis.get(getBtyesKey(key));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			returnRedisConnector(jedis);
		}
		return null;
	}
	
	public static void setValuesToPf(String key, String... values) {
		Jedis jedis = null;
		try {
			jedis = getRedisConnection();
			jedis.pfadd(key, values);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			returnRedisConnector(jedis);
		}
	}
	
	public static long getCountFromPf(String key) {
		Jedis jedis = null;
		try {
			jedis = getRedisConnection();
			return jedis.pfcount(key);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			returnRedisConnector(jedis);
		}
		return -1;
	}
	
	public static byte[] getBtyesKey(String key) {
		return key.getBytes();
	}
	
	public static void returnRedisConnector(Jedis jedis) {
		RedisConnector.getInstance().returnConnection(jedis);
	}
	
	public static Jedis getRedisConnection() {
		return RedisConnector.getInstance().getConnection();
	}
}
