/**
 * 
 */
package me.power.speed.common.cache.spring;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 * spring 4.1
 * http://jinnianshilongnian.iteye.com/blog/2105367
 * @author xuehui.miao
 *
 */
public class SpringAnnoCache {
	// /** 缓存config集合里的key为keyName，当满足方法参数keyName值的长度小于10时，使用缓存管理 */
	@Cacheable(value="config",key="#keyName",condition="#keyName.length()<10")
	public String cacheString() {
		return "";
	}
	
	///** 调用该方法时更新config缓存集合里，key为account对象里的name的缓存 */
	@CachePut(value="config",key="#account.getName()")
	public boolean updateCache() {
		return false;
	}
	
	/** 
    *  缓存config集合里的key为keyName，当满足方法参数keyName值的长度小于10时，
    *  清空缓存config集合里key为keyName的缓存,并且在为执行方法之前就执行 
    */
	@CacheEvict(value="config",key="#keyName",condition="#keyName.length()<10",beforeInvocation=true)
	///** 清空缓存config集合里的所有缓存 */
	//@CacheEvict(value="config",allEntries=true)
	public void clearCache() {
		
	}
}
