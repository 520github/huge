/**
 * 
 */
package me.power.speed.common.email;

/**
 * @author xuehui.miao
 *
 */
public class EmailModel {
	private final String from;  
    private final String to;  
    private final String subject;  
    private final String content;  
    private final String template;
    
    private EmailModel(Bulider bulider) {
		this.from = bulider.from;
		this.to = bulider.to;
		this.subject = bulider.subject;
		this.content = bulider.content;
		this.template = bulider.template;
	}
    
	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
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

	public static class Bulider {
		private String from;  
	    private String to;  
	    private String subject;  
	    private String content;  
	    private String template;
	    
	    public Bulider() {
	    	
	    }
	    
	    public Bulider from(String from) {
	    	this.from = from;
	    	return this;
	    }
	    
	    public Bulider to(String to) {
	    	this.to = to;
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
	    
	    public EmailModel build() {
	    	return new EmailModel(this);
	    }
	    
	}
}
