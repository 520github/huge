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
	protected static int measurements = 100;//测量次数
	protected static int threads = 10;//线程数
	protected static int SerialTimes = 10000;//每个线程执行序列化次数
	 
	protected void fail(Exception e) {
		e.printStackTrace();
		Assert.fail(e.getMessage());
	}
}
