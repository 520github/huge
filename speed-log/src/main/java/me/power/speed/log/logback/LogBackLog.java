/**
 * 
 */
package me.power.speed.log.logback;

import java.util.Map;

import me.power.speed.log.AbstractSlf4jLog;

/**
 * @author keke
 *
 */
public class LogBackLog extends AbstractSlf4jLog {
	
	/* (non-Javadoc)
	 * @see me.power.speed.log.AbstractSlf4jLog#setLogDynamicParameter()
	 */
	public void setLogDynamicParameter(Map<String,Object> dynamicParameter) {
		super.setDynamicParameter(dynamicParameter);
	}

}
