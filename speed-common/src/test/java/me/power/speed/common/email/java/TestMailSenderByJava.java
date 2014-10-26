/**
 * 
 */
package me.power.speed.common.email.java;

import org.junit.Before;
import org.junit.Test;

import me.power.speed.AbstractBaseTest;
import me.power.speed.common.email.EmailModel;

/**
 * @author xuehui.miao
 *
 */
public class TestMailSenderByJava extends AbstractBaseTest {
	private EmailModel emailModel;
	private MailSenderByJava mailSender;
	
	@Before
	public void before() {
		String host = "smtp.126.com";
		host = "smtp.ist.com.cn";
		String user = "liuyuan";
		String password = "maomao0425";
		String from = "mxh444@126.com";
		String to = "xuehui.miao@tendcloud.com";
		String subject ="this is test";
		String content= "template/template.vm";
		
		emailModel = new EmailModel.Bulider()
		.host(host).user(user).password(password)
		.from(from).to(to)
		.subject(subject).content(content).build();
	}
	
	@Test
	public void testMailSenderByJava() {
		try {
			mailSender = new MailSenderByJava(emailModel);
			mailSender.sendMessage();
			this.print("email send is ok.");
		} catch (Exception e) {
			this.fail(e);
		}
	}
}
