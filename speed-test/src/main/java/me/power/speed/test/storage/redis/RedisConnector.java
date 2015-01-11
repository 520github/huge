package me.power.speed.test.storage.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisConnector {
	private static final RedisConnector Instance = new RedisConnector();
	private static JedisPool pool = null;
	
	private static String host= "10.10.0.124";
	private static int port =6470;
	private static int timeout = 1000;
	private static int maxActive = 50;
	private static int maxIdle = 10;
	
	public static RedisConnector getInstance() {
		return Instance;
	}
	
	private void createSession(){
		if(pool == null){
			this.initParameter();
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(maxActive);
			config.setMaxIdle(maxIdle);
			config.setTestOnBorrow(true);
			try{
				pool = new JedisPool(config, host, port, timeout);
				if(pool == null)
					System.err.println("concurrent init redis pool failed.");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	private void initParameter() {
		String isHome = System.getProperty("isHome");
		if("true".equalsIgnoreCase(isHome)) {
			host = "localhost";
			port = 6379;
		}
	}
	
	/**
	 * 
	 * 获得Redis 连接池
	 * 
	 * @return
	 */
	public JedisPool getPool(){	
		createSession();
		return pool;
	}
	
	/**
	 * 
	 * 获得Redis 连接
	 * 
	 * @return
	 */
	public Jedis getConnection(){
		JedisPool pool = getPool();

		if (pool == null) {
			return null;
		}
		
		return pool.getResource();
	}
	
	public void returnConnection(Jedis jedis){
		if (pool == null || jedis == null) {
			return;
		}
		pool.returnResource(jedis);
	}
	
	/**
	 * 
	 * 关闭连接池
	 * 
	 */
	public void close(){
		if(pool!=null){
			pool.destroy();
		}
	}
}
