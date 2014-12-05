/**
 * 
 */
package me.power.speed.test.cache.dict;

/**
 * @author xuehui.miao
 *
 */
//https://github.com/Flipkart/GraceKelly/blob/master/core/src/main/java/lego/gracekelly/Kelly.java
//https://github.com/almadiverso/Cinnamon-Cache
//https://github.com/Cetsoft/imcache
//https://github.com/yangfuhai/ASimpleCache
//http://cache2k.org/
public interface DictCache<T> {
	
	public void defineInitKeys();
	
	public void initCache();
	
	public T getCache(String key);
	
	public T getCacheWhenEmptyAndInsert(String key);
	
	public boolean putCache(String key, T value);
}
