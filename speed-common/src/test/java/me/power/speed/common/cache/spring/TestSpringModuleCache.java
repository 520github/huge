/**
 * 
 */
package me.power.speed.common.cache.spring;

import org.junit.Before;
import org.junit.Test;

import me.power.speed.AbstractTest;

/**
 * @author xuehui.miao
 *
 */
public class TestSpringModuleCache extends AbstractTest {
	SpringModuleCache smc = null;
	
	@Before
	public void before() {
		smc = (SpringModuleCache)this.getBean("springModuleCache");
	}
	
	@Test
	public void testSpringModuleCache() {
		try {
			String name;
		      this.print("第一次访问，没有缓存");
		      name = smc.getName();
		      this.print(name);
		      name = smc.getName("Mr");
		      this.print(name);
		      
		      //use change not changed value
		      this.print("第二次访问，使用缓存");
		      smc.changeNameAndNotTellCache("Michael");
		      name = smc.getName();
		      this.print(name);
		      
		      name = smc.getName("Mr");
		      this.print(name);
		      
		      //update cache
		      this.print("清除缓存后，再次访问 ");
		      smc.setName("Michael");
		      name = smc.getName();
		      this.print(name);
		      
		      name = smc.getName("Mr");
		      this.print(name);
		} catch (Exception e) {
			this.fail(e);
		}
	}
}
