/**
 * 
 */
package me.power.speed.storage.redis.bitmap.impl;

import com.gameanalytics.bitmap.Bitmap;
import com.gameanalytics.bitmap.impl.ConciseBitmapImpl;

import redis.clients.jedis.Jedis;
import me.power.speed.storage.redis.bitmap.AbstractRedisBitmap;
import me.power.speed.storage.redis.bitmap.exception.RedisBitmapException;
import me.power.speed.storage.redis.bitmap.util.CompressBitmapUtil;

/**
 * @author xuehui.miao
 *
 */
public class RedisCompressBitmapImpl extends AbstractRedisBitmap {
	
	protected void onSetBitmapOffset(Jedis jedis, String key, int offset) throws RedisBitmapException {
		//从redis获取bitmap
		Bitmap bitmap = this.getBitmapFromJedisKey(jedis, key);
		//设置offset
		bitmap.set(offset);
		//存储bitmap到redis
		this.setBytesValue2Redis(jedis, key, this.getBytesFromBitmap(bitmap));
	}
	
	protected int OnGetBitmapCount(Jedis jedis, String key) throws RedisBitmapException {
		return this.getBitmapFromJedisKey(jedis, key).cardinary();
	}
	
	protected int onHandleBitmapOr(Jedis jedis, String... keys)
			throws RedisBitmapException {
		Bitmap result = null;
		for(String key: keys) {
			Bitmap bitmap = this.getBitmapFromJedisKey(jedis, key);
			if(result == null) {
				result = bitmap;
			}
			else {
				result = result.or(bitmap);
			}
		}
		return result.cardinary();
	}
	
	protected int onHandleBitmapAnd(Jedis jedis, String... keys)
			throws RedisBitmapException {
		Bitmap result = null;
		for(String key: keys) {
			Bitmap bitmap = this.getBitmapFromJedisKey(jedis, key);
			if(result == null) {
				result = bitmap;
			}
			else {
				result = result.and(bitmap);
			}
		}
		return result.cardinary();
	}
	
	/**
	 * 根据key，从redis获取内容，并转换成bitmap
	 * @param jedis
	 * @param key
	 * @return
	 */
	protected Bitmap getBitmapFromJedisKey(Jedis jedis, String key) {
		return this.getBitmapFromBytes(this.getBytesValueFromRedis(jedis, key));
	}
	
	/**
	 * 从字节数组反序列成bitmap对象
	 * @param datas
	 * @return
	 */
	protected Bitmap getBitmapFromBytes(byte[] datas) {
		if(datas == null) {
			return new ConciseBitmapImpl();
		}
		return CompressBitmapUtil.getBitmapFromBytes(datas);
	}
	
	/**
	 * 把bitmap对象序列化成字节数组
	 * @param bitmap
	 * @return
	 */
	protected byte[] getBytesFromBitmap(Bitmap bitmap) {
		return CompressBitmapUtil.getBytesFromBitmap(bitmap);
	}

}
