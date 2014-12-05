package me.power.speed.test.cache.provider.ehcache;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.CacheManager;

import me.power.speed.test.cache.CachingModel;
import me.power.speed.test.cache.exception.CacheException;
import me.power.speed.test.cache.provider.AbstractCacheProviderFacade;

public final class EhCacheFacade extends AbstractCacheProviderFacade {
	private Map caches = Collections.synchronizedMap(new HashMap());

	private CacheManager cacheManager;

	public void setCacheManager(CacheManager newCacheManager) {
		cacheManager = newCacheManager;
	}

	protected  Object onGetFromCache(Serializable key, CachingModel model) throws CacheException {
		return null;
	}

	protected Ehcache getCache(CachingModel model)
			throws CacheException {
		EhCacheCachingModel ehCacheCachingModel = (EhCacheCachingModel) model;
		String cacheName = ehCacheCachingModel.getCacheName();

		Ehcache cache = (Cache) caches.get(cacheName);
		if (cache == null) {
			cache = getCache(cacheName);
		}
		return cache;
	}

	protected Cache getCache(String name) throws CacheException {
		Cache cache = null;

		try {
			if (cacheManager.cacheExists(name)) {
				cache = cacheManager.getCache(name);
			}
		} catch (Exception exception) {
			//throw new CacheException(exception);
		}

		if (cache == null) {
			//throw new CacheException(name);
		}

		return cache;
	}

	@Override
	protected Object onGetFromCache(Serializable key,
			org.springmodules.cache.CachingModel model)
			throws org.springmodules.cache.CacheException {
		// TODO Auto-generated method stub
		return null;
	}

}
