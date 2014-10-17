package me.power.speed;

import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author xuehui.miao
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
public class AbstractTest extends AbstractBaseTest {
	protected static int measurements = 100;//��������
	protected static int threads = 10;//�߳���
	protected static int SerialTimes = 10000;//ÿ���߳�ִ�����л�����
	
	public void getBean() {
		ApplicationContextAware aa;
	}
	 
}
