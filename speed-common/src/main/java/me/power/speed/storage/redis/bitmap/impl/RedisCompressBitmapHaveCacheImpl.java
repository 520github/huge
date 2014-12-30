/**
 * 
 */
package me.power.speed.storage.redis.bitmap.impl;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

import com.gameanalytics.bitmap.Bitmap;
import com.gameanalytics.bitmap.impl.ConciseBitmapImpl;

import me.power.speed.storage.redis.bitmap.exception.RedisBitmapException;
import redis.clients.jedis.Jedis;

/**
 * @author xuehui.miao
 *
 */
public class RedisCompressBitmapHaveCacheImpl extends RedisCompressBitmapImpl {
	//当某个key的offset达到某个阈值时，提交缓存数据到redis
	private static int COMMIT_TO_REDIS_LIMIT_OFFSET = 10000;
	
	private static ConcurrentHashMap<String, Bitmap> keyBitmapCache = new ConcurrentHashMap<String, Bitmap>();
	
	private static ConcurrentHashMap<String, Set<Integer>> keyOffsetCache = 
			new ConcurrentHashMap<String, Set<Integer>>();
	
	protected void onSetBitmapOffset(Jedis jedis, String key, int offset) throws RedisBitmapException {
		this.setOffset2Cache(jedis, key, offset);
	}
	
	protected void setOffset2Cache(Jedis jedis, String key, int offset) {
		Set<Integer> keyOffset = keyOffsetCache.get(key);
		if(keyOffset == null) {
			keyOffset = new ConcurrentSkipListSet<Integer>();
		}
		keyOffset.add(offset);
		keyOffsetCache.put(key, keyOffset);
		
		//提交数据到redis服务器
		if(keyOffset.size() >= COMMIT_TO_REDIS_LIMIT_OFFSET) {
			Bitmap newBitmap = this.getNewBitmapFromCacheOffset(key);
			keyOffset.clear();
			Bitmap cacheBitmap = this.getBitmapFromCache(key);
			if(cacheBitmap == null) {
				cacheBitmap = newBitmap;
			}
			else {
				cacheBitmap = cacheBitmap.or(newBitmap);
			}
			//设置到缓存
			this.setBitmap2Cache(key, cacheBitmap);
			//同步到redis服务器
			this.setBytesValue2Redis(jedis, key, this.getBytesFromBitmap(cacheBitmap));
		}
	}
	
	protected void setBitmap2Cache(String key, Bitmap bitmap) {
		keyBitmapCache.put(key, bitmap);
	}
	
	/**
	 * 根据key获取bitmap缓存
	 * @param key
	 * @return
	 */
	protected Bitmap getBitmapFromCache(String key) {
		return keyBitmapCache.get(key);
	}
	
	/**
	 * 根据某个key缓存的offset生成一个bitmap
	 * @param key
	 * @return
	 */
	protected Bitmap getNewBitmapFromCacheOffset(String key) {
		Bitmap newBitmap = new ConciseBitmapImpl();
		Set<Integer> keyOffset = keyOffsetCache.get(key);
		if(keyOffset == null) {
			return newBitmap;
		}
		
		for(Integer offset: keyOffset) {
			newBitmap.set(offset);
		}
		return newBitmap;
	}
}
