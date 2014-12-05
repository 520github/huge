/**
 * 
 */
package me.power.speed.test.cache.provider;

import java.io.Serializable;

import org.springmodules.cache.CacheException;
import org.springmodules.cache.CachingModel;

/**
 * @author xuehui.maio
 *
 */
public abstract class AbstractCacheProviderFacade implements CacheProviderFacade {

	/* (non-Javadoc)
	 * @see me.power.speed.test.cache.provider.CacheProviderFacade#getFromCache(java.io.Serializable, org.springmodules.cache.CachingModel)
	 */
	public final Object getFromCache(Serializable key, CachingModel model)
			throws CacheException {
		
		return null;
	}
	
	protected abstract Object onGetFromCache(Serializable key, CachingModel model) throws CacheException;

}
