/**
 * 
 */
package me.power.speed.test.cache.dict;

import org.junit.Before;
import org.junit.Test;

import me.power.speed.test.AbstractSpringTest;

/**
 * @author xuehui.miao
 *
 */
public class TestProductDictCache extends AbstractSpringTest {
	private ProductDictCache cache = new ProductDictCache();
	
	@Before
	public void before() {
		cache.defineInitKeys();
		cache.initCache();
	}
	
	@Test
	public void testGetCache() {
		String key = "14340";
		Object value = cache.getCache(key);
		this.print(value);
	}
	
}
