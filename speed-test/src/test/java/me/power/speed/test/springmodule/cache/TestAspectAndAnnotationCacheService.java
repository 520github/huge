/**
 * 
 */
package me.power.speed.test.springmodule.cache;

import org.junit.Before;
import org.junit.Test;

import me.power.speed.test.AbstractSpringTest;

/**
 * @author xuehui.miao
 *
 */
public class TestAspectAndAnnotationCacheService extends AbstractSpringTest {
	private AspectAndAnnotationCacheService service;
	
	@Before
	public void before() {
		try {
			service = (AspectAndAnnotationCacheService) this.getBean("aspectAndAnnotationCacheService");
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	@Test
	public void testAspectAndAnnotationCacheService() {
		String key = "11";
		String value = service.getAspectAndAnnotationCache(key);
		this.print(value);
		value = service.getAspectAndAnnotationCache(key);
		this.print(value);
		
		key = "22";
		value = service.getAspectAndAnnotationCache(key);
		this.print(value);
		
		value = service.getAspectAndAnnotationCache(key);
		this.print(value);
		
	}
}
