package me.power.speed.test.cache.provider;

import java.io.Serializable;

import org.springmodules.cache.CacheException;
import org.springmodules.cache.CachingModel;

public interface CacheProviderFacade {
	Object getFromCache(Serializable key, CachingModel model) throws CacheException;
}
