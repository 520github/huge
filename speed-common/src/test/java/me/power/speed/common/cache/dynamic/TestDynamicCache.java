/**
 * 
 */
package me.power.speed.common.cache.dynamic;

import org.junit.Test;

import me.power.speed.AbstractBaseTest;

/**
 * @author xuehui.miao
 *
 */
public class TestDynamicCache extends AbstractBaseTest {
	private DynamicCache cache = DynamicCache.getInstance();
	
	@Test
	public void testDynamicCache() {
		String methodName = "getData";
		Object parameters[] = new Object[]{};
		Object obj = new CacheData("12344");
		Object result = cache.getCacheData(obj, methodName, parameters, 0, 0);
		this.print(result);
		result = cache.getCacheData(obj, methodName, parameters, 0, 0);
		this.print(result);
	}
}
