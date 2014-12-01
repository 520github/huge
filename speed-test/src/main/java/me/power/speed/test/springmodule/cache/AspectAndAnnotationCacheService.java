/**
 * 
 */
package me.power.speed.test.springmodule.cache;

import java.util.UUID;

/**
 * @author xuehui.miao
 *
 */
public class AspectAndAnnotationCacheService {
	
	@CacheAnnotation(cacheModelKey="deep")
	public String getAspectAndAnnotationCache(String key) {
		return key + ":" + UUID.randomUUID().toString();
	}
}
