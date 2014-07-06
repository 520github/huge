/**
 * 
 */
package me.power.speed;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author xuehui.miao
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
public class AbstractTest {
	protected void fail(Exception e) {
		e.printStackTrace();
		Assert.fail(e.getMessage());
	}
}
