/**
 * 
 */
package me.power.speed.test.cache.dict;

/**
 * @author xuehui.miao
 *
 */
public interface DictCache<T> {
	
	public void defineInitKeys();
	
	public void initCache();
	
	public T getCache(String key);
	
	public T getCacheWhenEmptyAndInsert(String key);
	
	public boolean putCache(String key, T value);
}
