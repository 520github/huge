/**
 * 
 */
package me.power.speed.storage.redis.bitmap;

import org.apache.commons.lang.StringUtils;

import me.power.speed.storage.redis.bitmap.exception.RedisBitmapException;
import redis.clients.jedis.Jedis;

/**
 * @author xuehui.miao
 *
 */
public abstract class AbstractRedisBitmap implements RedisBitmap {

	/* (non-Javadoc)
	 * @see me.power.speed.storage.redis.bitmap.RedisBitmap#setBitmapOffset(java.lang.String, int)
	 */
	public final void setBitmapOffset(Jedis jedis, String key, int offset) throws RedisBitmapException {
		this.checkParameter(jedis, key);
		this.onSetBitmapOffset(jedis, key, offset);
	}
	
	/**
	 * 设置一个offset到 bitmap中
	 * @param jedis
	 * @param key
	 * @param offset
	 * @throws RedisBitmapException
	 */
	protected abstract void onSetBitmapOffset(Jedis jedis, String key, int offset) throws RedisBitmapException ;
	
	public final int getBitmapCount(Jedis jedis, String key) throws RedisBitmapException {
		this.checkParameter(jedis, key);
		return this.OnGetBitmapCount(jedis, key);
	}
	
	/**
	 * 获取bitmap的统计数
	 * @param jedis
	 * @param key
	 * @return
	 * @throws RedisBitmapException
	 */
	protected abstract int OnGetBitmapCount(Jedis jedis, String key) throws RedisBitmapException;
	
	
	public final int handleBitmapOr(Jedis jedis, String...keys) throws RedisBitmapException {
		this.checkParameter(jedis, keys);
		return this.onHandleBitmapOr(jedis, keys);
	}
	
	/**
	 * 多个key的bitmap进行or操作
	 * @param jedis
	 * @param keys
	 * @return
	 * @throws RedisBitmapException
	 */
	protected abstract int onHandleBitmapOr(Jedis jedis, String...keys) throws RedisBitmapException;
	
	public final int handleBitmapAnd(Jedis jedis, String...keys) throws RedisBitmapException {
		this.checkParameter(jedis, keys);
		return this.onHandleBitmapAnd(jedis, keys);
	}
	
	/**
	 * 多个key的bitmap进行and操作
	 * @param jedis
	 * @param keys
	 * @return
	 * @throws RedisBitmapException
	 */
	protected abstract int onHandleBitmapAnd(Jedis jedis, String...keys) throws RedisBitmapException;
	
	/**
	 * 检查参数设置
	 * @param jedis
	 * @param key
	 * @throws RedisBitmapException
	 */
	protected void checkParameter(Jedis jedis, String key) throws RedisBitmapException {
		this.checkJedisConnection(jedis);
		this.checkKey(key);
	}
	
	/**
	 * 检查参数设置
	 * @param jedis
	 * @param keys
	 * @throws RedisBitmapException
	 */
	protected void checkParameter(Jedis jedis, String... keys) throws RedisBitmapException {
		this.checkJedisConnection(jedis);
		this.checkKeys(keys);
	}
	
	protected void checkJedisConnection(Jedis jedis) throws RedisBitmapException {
		if(jedis == null) {
			throw new RedisBitmapException("jedis connection is null");
		}
	}
	
	protected void checkKey(String key) throws RedisBitmapException {
		if(StringUtils.isBlank(key)) {
			throw new RedisBitmapException("handle redis key is empty");
		}
	}
	
	protected void checkKeys(String... keys) throws RedisBitmapException {
		if(keys == null || keys.length < 1) {
			throw new RedisBitmapException("handle redis keys is empty");
		}
	}
	
	protected byte[] getBytesValueFromRedis(Jedis jedis, String key) {
		return jedis.get(this.getBtyesKey(key));
	}
	
	protected void setBytesValue2Redis(Jedis jedis, String key, byte datas[]) {
		jedis.set(this.getBtyesKey(key), datas);
	}
	
	private byte[] getBtyesKey(String key) {
		return key.getBytes();
	}

}
