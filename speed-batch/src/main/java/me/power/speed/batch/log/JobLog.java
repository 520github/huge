/**
 * 
 */
package me.power.speed.batch.log;

import me.power.speed.log.AbstractSlf4jLog;

/**
 * @author keke
 *
 */
public class JobLog extends AbstractSlf4jLog {
	
	private static JobLog jobLog = new JobLog();
	
	private JobLog() {
		this.setLogOneDynamicParameter(LogDynamicFiled.logType, "job");
	}
	
	public static JobLog getInstance() {
		if(jobLog == null) {
			jobLog = new JobLog();
		}
		return jobLog;
	}
}
