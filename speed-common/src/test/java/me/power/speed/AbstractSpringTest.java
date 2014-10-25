/**
 * 
 */
package me.power.speed;

import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * @author xuehui.miao
 *
 */
@ContextConfiguration("classpath:/applicationContext.xml")
//@ContextConfiguration(locations = { "/spring/applicationContext.xml" })
public abstract class AbstractSpringTest extends AbstractJUnit4SpringContextTests {
	public <T> T getBean(Class<T> type) {
	    return applicationContext.getBean(type);
	}
	 
	public Object getBean(String beanName) {
	    return applicationContext.getBean(beanName);
	}
	 
	protected ApplicationContext getContext() {
	    return applicationContext;
	}
}
