/**
 * 
 */
package me.power.speed.common.email;

import java.util.List;

/**
 * @author xuehui.miao
 *
 */
public class EmailModel {
	private final String host;
	private final String protocol;
	private final String user;
	private final String password;
	private final String from;  
    private final String to;  
    private final String cc;//抄送
    private final String bcc;//秘送
    private final String subject;  
    private final String content;  
    private final String template;
    private final List<String> attachList;//附件列表
    
    private EmailModel(Bulider bulider) {
    	this.host = bulider.host;
    	this.protocol = bulider.protocol;
    	this.user = bulider.user;
    	this.password = bulider.password;
		this.from = bulider.from;
		this.to = bulider.to;
		this.cc = bulider.cc;
		this.bcc = bulider.bcc;
		this.subject = bulider.subject;
		this.content = bulider.content;
		this.template = bulider.template;
		this.attachList = bulider.attachList;
	}
    
	public String getHost() {
		return host;
	}
	
	public String getProtocol() {
		return protocol;
	}

	public String getUser() {
		return user;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public String getCc() {
		return cc;
	}

	public String getBcc() {
		return bcc;
	}

	public String getSubject() {
		return subject;
	}

	public String getContent() {
		return content;
	}
	
	public String getTemplate() {
		return template;
	}
	
	public List<String> getAttachList() {
		return attachList;
	}

	public static class Bulider {
		private String host;
		private String protocol;
		private String user;
		private String password;
		private String from;  
	    private String to;  
	    private String cc;//抄送
	    private String bcc;//秘送
	    private String subject;  
	    private String content;  
	    private String template;
	    private List<String> attachList;//附件列表
	    
	    public Bulider() {
	    	
	    }
	    
	    public Bulider host(String host) {
	    	this.host = host;
	    	return this;
	    }
	    
	    public Bulider protocol(String protocol) {
	    	this.protocol = protocol;
	    	return this;
	    }
	    
	    public Bulider user(String user) {
	    	this.user = user;
	    	return this;
	    }
	    
	    public Bulider password(String password) {
	    	this.password = password;
	    	return this;
	    }
	    
	    public Bulider from(String from) {
	    	this.from = from;
	    	return this;
	    }
	    
	    public Bulider to(String to) {
	    	this.to = to;
	    	return this;
	    }
	    
	    public Bulider cc(String cc) {
	    	this.cc = cc;
	    	return this;
	    }
	    
	    public Bulider bcc(String bcc) {
	    	this.bcc = bcc;
	    	return this;
	    }
	    
	    public Bulider subject(String subject) {
	    	this.subject = subject;
	    	return this;
	    }
	    
	    public Bulider content(String content) {
	    	this.content = content;
	    	return this;
	    }
	    
	    public Bulider template(String template) {
	    	this.template = template;
	    	return this;
	    }
	    
	    public Bulider attachList(List<String> attachList) {
	    	this.attachList = attachList;
	    	return this;
	    }
	    
	    public EmailModel build() {
	    	return new EmailModel(this);
	    }
	    
	}
}
