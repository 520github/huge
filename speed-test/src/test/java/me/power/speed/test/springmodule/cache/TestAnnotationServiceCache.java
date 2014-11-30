/**
 * 
 */
package me.power.speed.test.springmodule.cache;

import org.junit.Before;
import org.junit.Test;

import me.power.speed.test.AbstractSpringTest;

/**
 * @author keke
 *
 */
public class TestAnnotationServiceCache extends AbstractSpringTest {
	private AnnotationDictService ads = null;
	
	@Before
	public void before() {
		ads = (AnnotationDictService)this.getBean("dictService");
	}
	
	@Test
	public void testAnnotationServiceCache() {
		String key = "123";
		String value = ads.getDictValue(key);
		this.print(value);
		
		value = ads.getDictValue(key);
		this.print(value);
		
		//ads.setDictValue(key, "xiaolala");
		value = ads.getDictValue("llll");
		this.print(value);
		
		value = ads.getDictValue("llll");
		this.print(value);
		
		value = ads.getDictValue(key);
		this.print(value);
	}
}
