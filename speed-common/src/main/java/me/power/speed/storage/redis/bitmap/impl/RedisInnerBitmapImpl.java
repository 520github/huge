package me.power.speed.storage.redis.bitmap.impl;

import redis.clients.jedis.Jedis;
import me.power.speed.storage.redis.bitmap.AbstractRedisBitmap;
import me.power.speed.storage.redis.bitmap.exception.RedisBitmapException;

public class RedisInnerBitmapImpl extends AbstractRedisBitmap {

	@Override
	protected void onSetBitmapOffset(Jedis jedis, String key, int offset)
			throws RedisBitmapException {
		jedis.setbit(key, offset, true);
	}

	@Override
	protected int OnGetBitmapCount(Jedis jedis, String key)
			throws RedisBitmapException {
		return (int)jedis.bitcount(key).longValue();
	}

	@Override
	protected int onHandleBitmapOr(Jedis jedis, String... keys)
			throws RedisBitmapException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int onHandleBitmapAnd(Jedis jedis, String... keys)
			throws RedisBitmapException {
		// TODO Auto-generated method stub
		return 0;
	}

}
