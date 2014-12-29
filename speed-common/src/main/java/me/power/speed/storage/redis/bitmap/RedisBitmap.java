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
	public void setBitmapOffset(Jedis jedis, String key, int offset) throws RedisBitmapException;
	
	public int getBitmapCount(Jedis jedis, String key) throws RedisBitmapException;
	
	public int handleBitmapOr(Jedis jedis, String...keys) throws RedisBitmapException;
	
	public int handleBitmapAnd(Jedis jedis, String...keys) throws RedisBitmapException;
}
