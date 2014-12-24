package me.power.speed;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author xuehui.miao
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext*.xml")
public class AbstractTest extends AbstractBaseTest {
	
	@Autowired
	protected ApplicationContext ctx;
	
	public Object getBean(String name) {
		return ctx.getBean(name);
	}
	 
}
