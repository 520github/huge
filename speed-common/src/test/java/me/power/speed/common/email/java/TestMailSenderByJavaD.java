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
public class TestMailSenderByJavaD extends AbstractBaseTest {
	private EmailModel emailModel;
	private MailSenderByJava mailSender;
	
	@Before
	public void before() {
		String host = "";
		host = "";
		String user = "";
		String password = "";
		String from = "";
		String to = "";
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
