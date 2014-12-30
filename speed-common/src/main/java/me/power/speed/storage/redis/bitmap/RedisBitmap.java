/**
 * 
 */
package me.power.speed.storage.redis.bitmap;

import me.power.speed.storage.redis.bitmap.exception.RedisBitmapException;
import redis.clients.jedis.Jedis;

/**
 * @author xuehui.miao
 *
 */
public interface RedisBitmap {
	
	/**
	 * 设置一个offset到bitmap
	 * @param jedis
	 * @param key
	 * @param offset
	 * @throws RedisBitmapException
	 */
	public void setBitmapOffset(Jedis jedis, String key, int offset) throws RedisBitmapException;
	
	
	/**
	 * 获取bitmap的统计数
	 * @param jedis
	 * @param key
	 * @return
	 * @throws RedisBitmapException
	 */
	public int getBitmapCount(Jedis jedis, String key) throws RedisBitmapException;
	
	
	/**
	 * 获取bitmap对应的字节数组
	 * @param jedis
	 * @param key
	 * @return
	 * @throws RedisBitmapException
	 */
	public byte[] getBitmapBytes(Jedis jedis, String key) throws RedisBitmapException;
	
	
	/**
	 * 对多个key对应的bitmap进行or运算，并返回结算结果
	 * @param jedis
	 * @param keys
	 * @return
	 * @throws RedisBitmapException
	 */
	public int handleBitmapOr(Jedis jedis, String...keys) throws RedisBitmapException;
	
	
	/**
	 * 对多个key对应的bitmap进行and运算，并返回结算结果
	 * @param jedis
	 * @param keys
	 * @return
	 * @throws RedisBitmapException
	 */
	public int handleBitmapAnd(Jedis jedis, String...keys) throws RedisBitmapException;
}
