/**
 * 
 */
package me.power.speed.batch.log;

import me.power.speed.log.logback.LogBackConfigLoader;

/**
 * @author keke
 *
 */
public class LogPortal {
	
	static {
		try {
			LogBackConfigLoader.loadFromClassPath("logback-speed.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static JobLog getJobLog() {
		return JobLog.getInstance();
	}
}
