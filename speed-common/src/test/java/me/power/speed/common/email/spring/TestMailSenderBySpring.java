/**
 * 
 */
package me.power.speed.common.email.spring;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import me.power.speed.AbstractTest;
import me.power.speed.common.email.EmailModel;

/**
 * @author xuehui.miao
 *
 */
//http://gundumw100.iteye.com/blog/515346
public class TestMailSenderBySpring extends AbstractTest {
	//@Qualifier(name="mailSenderBySpring")
	@Resource(name = "mailSenderBySpring")
	private MailSenderBySpring mailSender;
	
	private EmailModel emailModel;
	private Map<String,Object> model = new HashMap<String, Object>();
	
	@Before
	public void before() {
		String from = "";
		String to = "";
		String subject ="this is test";
		String template= "template/template.vm";
		emailModel = new EmailModel.Bulider().from(from).to(to).subject(subject).template(template).build();
	}
	
	@Test
	public void testMailSenderBySpring() {
		try {
			this.createMailSenderBySpring();
			mailSender.setEmailProperties(emailModel);
			mailSender.sendEmailByTemplate(model);
		} catch (Exception e) {
			this.fail(e);
		}
		
	}
	
	private void createMailSenderBySpring() {
		if(this.mailSender == null) {
			mailSender = (MailSenderBySpring)this.getBean("mailSenderBySpring");
		}
		if(this.mailSender == null) {
			this.print("can't get mailSenderBySpring object.");
		}
	}
}
