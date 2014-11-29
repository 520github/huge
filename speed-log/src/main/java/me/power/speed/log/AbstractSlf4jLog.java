/**
 * 
 */
package me.power.speed.log;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * @author keke
 *
 */
public abstract class AbstractSlf4jLog {
	public static boolean isPrintSystemOut = true;
	private Map<String,Object> dynamicParameter ;
	private static final Logger log = LoggerFactory.getLogger("log");
	
	public void logDebug(String msg) {
		logDebug(msg, this.getEmptyObject());
	}
	
	public void logDebug(String format, Object... obj) {
		try {
			this.setLogDynamicParameter();
			this.printSystemOut(format);
			log.debug(format, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void logInfo(String msg) {
		logInfo(msg, this.getEmptyObject());
	}
	
	public void logInfo(String format, Object... obj) {
		try {
			this.setLogDynamicParameter();
			this.printSystemOut(format);
			log.info(format, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void logError(String msg) {
		logError(msg, this.getEmptyObject());
	}
	
	public void logError(String format, Object... obj) {
		try {
			this.setLogDynamicParameter();
			this.printSystemOut(format);
			log.error(format, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void logError(String msg, Throwable t) {
		try {
			this.setLogDynamicParameter();
			this.printSystemOut(msg, t);
			log.error(msg, t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Object getEmptyObject() {
		return null;
	}
	
	private void printSystemOut(String message) {
		this.printSystemOut(message, null);
	}
	
	private void printSystemOut(String message, Throwable t) {
		if(!isPrintSystemOut) {
			return;
		}
		System.out.println(message);
		if(t != null) {
			t.printStackTrace();
		}
	}
	
	private void setLogDynamicParameter() {
		if(dynamicParameter == null || dynamicParameter.isEmpty()) {
			return;
		}
		MDC.setContextMap(dynamicParameter);
	}
	
	public void setLogDynamicParameter(Map<String,Object> dynamicParameter) {
		this.dynamicParameter = dynamicParameter;
	}
	
	public void setLogOneDynamicParameter(String key, Object value) {
		if(this.dynamicParameter == null) {
			this.dynamicParameter = new HashMap<String, Object>();
		}
		this.dynamicParameter.put(key, value);
	}

	public void setDynamicParameter(Map<String, Object> dynamicParameter) {
		this.dynamicParameter = dynamicParameter;
	}
	
}
