/**
 * 
 */
package me.power.speed.huge.i18n.spring;

import java.io.UnsupportedEncodingException;  
import java.text.MessageFormat;  
import java.util.Locale;  
import java.util.Map;  
import java.util.concurrent.ConcurrentHashMap;  

import org.apache.commons.lang.StringUtils;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

/**
 * @author xuehui.miao
 *
 */
@Service
public class ResourceBundleMessageSourceExtend extends
		ResourceBundleMessageSource {
	
	private static final String ENCODING = "GBK";// 注意属性文件使用GBK编码  
    private static final String NULL = "null";  
    
    Map<String, String> encodingCache = new ConcurrentHashMap<String, String>(20); 
    
    protected String resolveCodeWithoutArguments(String code, Locale locale) {  
        String message = super.resolveCodeWithoutArguments(code, locale);  
        return decodeString(message, ENCODING);  
  
    }
    
    protected MessageFormat createMessageFormat(String msg, Locale locale) {  
        if (logger.isDebugEnabled()) {  
            logger.debug("Creating MessageFormat for pattern [" + msg  
                    + "] and locale '" + locale + "'");  
        }  
        msg = decodeString(msg, ENCODING);  
        return new MessageFormat((msg != null ? msg : ""), locale);  
    } 
    
    private String decodeString(String message, String encode) {  
    	if(StringUtils.isBlank(message)) {
    		return message;
    	}
    	System.out.println("before value:" + message);
        String encodMessage = encodingCache.get(message);  
        if (encodMessage == null) {  
            try {  
                encodMessage = new String(message.getBytes("ISO8859-1"), encode);  
                if (message != null) {  
                    encodingCache.put(message, encodMessage);  
                } else {  
                    encodingCache.put(message, NULL);  
                }  
            } catch (UnsupportedEncodingException e) {  
                e.printStackTrace();  
            }  
        }  
        return encodMessage;  
    }
}
