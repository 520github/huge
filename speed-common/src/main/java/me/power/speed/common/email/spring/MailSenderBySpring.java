/**
 * 
 */
package me.power.speed.common.email.spring;

import java.util.Map;

import me.power.speed.common.email.EmailModel;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 * 
 * @author xuehui.miao
 *
 */
@Service("mailSenderBySpring")
public class MailSenderBySpring {
	//@Qualifier
	//@Qualifier(value="mailSender")
	@Autowired
	private JavaMailSender mailSender;
	
	//@Qualifier("velocityEngine")
	@Autowired
	private VelocityEngine velocityEngine;
	
	private SimpleMailMessage simpleMailMessage;
	
	private EmailModel emailProperties;
	
	public void setEmailProperties(EmailModel emailProperties) {
		this.emailProperties = emailProperties;
		if(simpleMailMessage == null) {
			this.simpleMailMessage = new SimpleMailMessage();
		}
		simpleMailMessage.setFrom(this.emailProperties.getFrom());
		simpleMailMessage.setTo(this.emailProperties.getTo());
		simpleMailMessage.setSubject(this.emailProperties.getSubject());
	}

	public void sendEmailByTemplate(Map<String,Object> model) {
		String content = this.getContent(this.emailProperties.getTemplate(), model);
		simpleMailMessage.setText(content);
		this.mailSender.send(simpleMailMessage);
	}
	
	private String getContent(String template, Map<String,Object> model) {
		return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template, model);
	}
	
}
