package me.power.speed.test.cache.provider.ehcache;

import me.power.speed.test.cache.CachingModel;

public class EhCacheCachingModel implements CachingModel {
	private String cacheName;
	
	public EhCacheCachingModel() {
	}
	
	public EhCacheCachingModel(String cacheName) {
		setCacheName(cacheName);
	}

	public final String getCacheName() {
		return cacheName;
	}

	public final void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}
	
	
}
