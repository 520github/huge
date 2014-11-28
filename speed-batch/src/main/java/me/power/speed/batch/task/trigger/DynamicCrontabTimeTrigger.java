/**
 * 
 */
package me.power.speed.batch.task.trigger;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.CronTriggerBean;


/**
 * 
 * @author xuehui.miao
 *
 */
public class DynamicCrontabTimeTrigger extends CronTriggerBean {
	
	private static final long serialVersionUID = -2278805192895742049L;
//	@Autowired
//	private DictService dictService;
	
	public DynamicCrontabTimeTrigger() {
	}
	
	public void afterPropertiesSet() throws Exception {
		this.setCrontabExpression();
		super.afterPropertiesSet();
	}
	
	private void setCrontabExpression() {
		try {
//			String cronExpression = dictService.getParaConfig(CommonConstant.WEEK_REPORT_CRON_EXPRESSION);
//			LogUtil.logDebug("cronExpression: " + cronExpression);
			//String cronExpression = "get from db";
			//this.setCronExpression(cronExpression);
			System.out.println("setCrontabExpression");
		} catch (Exception e) {
//			LogUtil.logError("DynamicCrontabTimeTrigger get cronException: " + e.getMessage(), e);
		}
	}
}
