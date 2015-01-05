package me.power.speed.test.storage.redis;

import java.util.List;

import me.power.speed.common.regular.JdkRegularUtil;
import redis.clients.jedis.Jedis;

public class RedisUtil {
	
	public interface RedisHandle {
		public Object redisHandle(Jedis jedis);
	}
	
	public static Object handleRedis(RedisHandle handle) {
		Jedis jedis = null;
		try {
			jedis = getRedisConnection();
			return handle.redisHandle(jedis);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			returnRedisConnector(jedis);
		}
		return null;
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
	
	public static void addValuesToSet(final String key, final String...values) {
		handleRedis(new RedisHandle() {
			public Object redisHandle(Jedis jedis) {
				return jedis.sadd(key, values);
			}
		});
	}
	
	public static String eval(final String echo) {
		return (String)handleRedis(new RedisHandle() {
			public Object redisHandle(Jedis jedis) {
				return jedis.eval(echo);
			}
		});
	}
	
	public static String info() {
		return (String)handleRedis(new RedisHandle() {
			public Object redisHandle(Jedis jedis) {
				return jedis.info();
			}
		});
	}
	
	public static String getInfoItem(String pattern) {
		String info = info();
		List<String> resultList = JdkRegularUtil.getGroupListByPattern(info, pattern);
		if(resultList == null || resultList.size() < 1) {
			return "";
		}
		return resultList.get(0);
	}
	
	public static long getRedisCurrentUsedMemory() {
		String pattern = "used_memory:[0-9]+";
		String infoItem = getInfoItem(pattern);
		List<String> result = JdkRegularUtil.getGroupListByPattern(infoItem, "[0-9]+");
		if(result == null || result.size() < 1) {
			return 0;
		}
		return Long.valueOf(result.get(0));
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
