/**
 * 
 */
package me.power.speed.common.email.java;

/**
 * @author xuehui.miao
 *
 */

import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.Multipart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeBodyPart;
import org.apache.commons.lang.StringUtils;
import me.power.speed.common.email.EmailModel;

public class MailSenderByJava {
	private static final String MAIL_CONNECT_TYPE_HOST = "mail.smtp.host";
	private static final String MAIL_CONNECT_TYPE_AUTH = "mail.smtp.auth";
	
	protected Session session = null;
	protected MimeMessage message = null;
	protected Multipart multipart = null;
	private EmailModel emailModel;
	
	public MailSenderByJava(EmailModel emailModel) throws Exception {
		this.emailModel = emailModel;
		this.initStartEmail();
	}
	
	private void initStartEmail() throws Exception {
		this.initMail();
		this.setSender();
		this.setReceiver();
		this.setSubject();
		this.addContent();
		this.addAttach();
		this.addMultipart2Message();
	}
	
	private void initMail() throws Exception {
		Properties prop = System.getProperties();
		prop.put(MAIL_CONNECT_TYPE_HOST, emailModel.getHost());
		prop.put(MAIL_CONNECT_TYPE_AUTH, "true");
		
		//Authenticator auth = new PasswordAuthentication();
		session = Session.getDefaultInstance(prop);
		message = new MimeMessage(session);
	}
	
	private void setSubject() throws Exception {
		message.setSubject(emailModel.getSubject());
	}
	
	private void setSender() throws Exception {
		message.setFrom(new InternetAddress(emailModel.getFrom()));
	}
	
	private void setReceiver() throws Exception {
		if(StringUtils.isNotBlank(emailModel.getTo())) {//ÉèÖÃÊÕÐÅÈË
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailModel.getTo()));
		}
		
		if(StringUtils.isNotBlank(emailModel.getCc())) {//ÉèÖÃ³­ËÍÈË
			message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(emailModel.getCc()));
		}
		
		if(StringUtils.isNotBlank(emailModel.getBcc())) {//ÉèÖÃ³­ËÍÈË
			message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(emailModel.getBcc()));
		}
	}
	
	private void addContent() throws Exception {
		if(multipart == null) multipart = new MimeMultipart();
		MimeBodyPart content = new MimeBodyPart();
		content.setText(emailModel.getContent());
		multipart.addBodyPart(content);
	}
	
	private void addAttach() throws Exception {
		if(multipart == null) multipart = new MimeMultipart();
		if(emailModel.getAttachList() == null || emailModel.getAttachList().size() < 1) {
			return ;
		}
		
		List<String> attachList = emailModel.getAttachList();
		for (int i = 0; i < attachList.size(); i++) {
			String fileName = (String)attachList.get(i);
			if(StringUtils.isBlank(fileName)) {
				continue;
			}
			
			MimeBodyPart attach = new MimeBodyPart();
			FileDataSource fds = new FileDataSource(fileName);
			attach.setDataHandler(new DataHandler(fds));
			attach.setFileName(fileName);
			
			multipart.addBodyPart(attach);
		}
	}
	
	private void addMultipart2Message() throws Exception {
		message.setContent(multipart);
		message.saveChanges();
	}
	
	protected void sendMessage() throws Exception {
		Transport tsport = null;
		try {
			String protocol = emailModel.getProtocol();
			if(StringUtils.isBlank(protocol)) {
				protocol = "smtp";
			}
			tsport = session.getTransport(protocol);
			tsport.connect(emailModel.getHost(), emailModel.getUser(), emailModel.getPassword());
			tsport.sendMessage(message, message.getAllRecipients());
		} catch (Exception e) {
			throw e;
		} finally {
			if(tsport != null)tsport.close();
		}
	}
}
