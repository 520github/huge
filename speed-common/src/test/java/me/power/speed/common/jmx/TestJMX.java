/**
 * 
 */
package me.power.speed.common.jmx;

import me.power.speed.AbstractBaseTest;

import org.junit.Test;

/**
 * @author xuehui.miao
 *
 */
public class TestJMX extends AbstractBaseTest {
	@Test
	public void testJMX() {
		try {
			RegisterMBean.registerMBean();
			System.out.println("Waiting forever...");
			Thread.sleep(Long.MAX_VALUE);
		} catch (Exception e) {
			this.fail(e);
		}
	}
}
