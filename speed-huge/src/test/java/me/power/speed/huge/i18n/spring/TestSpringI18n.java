/**
 * 
 */
package me.power.speed.huge.i18n.spring;

import java.util.Locale;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xuehui.miao
 *
 */
public class TestSpringI18n  {
	
	@Test
	public void testSpringI18n() {
		try {
			ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
			ResourceBundleMessageSourceExtend resourceBean = (ResourceBundleMessageSourceExtend)ac.getBean("resource");
			String result = resourceBean.getMessage("message.user.username", null, Locale.CHINA);
			//this.print(result);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
