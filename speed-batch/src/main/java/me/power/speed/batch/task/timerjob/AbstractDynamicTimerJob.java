/**
 * 
 */
package me.power.speed.batch.task.timerjob;

import java.util.ArrayList;
import java.util.List;

import me.power.speed.batch.task.handle.TaskHandle;

import org.apache.commons.lang.StringUtils;
import org.quartz.Scheduler;
import org.springframework.scheduling.quartz.CronTriggerBean;

/**
 * @author xuehui.miao
 *
 */
public abstract class AbstractDynamicTimerJob extends AbstractTimerJob {
	//定时处理器
	private Scheduler scheduler;
	//处理的任务链
	private List<TaskHandle> taskHandleChain;
	//触发器实例名
	private String triggerName;
	
	protected void addTaskHandle(TaskHandle taskHandle) {
		if(taskHandle == null) {
			return;
		}
		if(taskHandleChain == null) {
			taskHandleChain = new ArrayList<TaskHandle>();
		}
		taskHandleChain.add(taskHandle);
	}
	
	/* (non-Javadoc)
	 * @see me.power.speed.batch.task.timerjob.TimerJob#doJob()
	 */
	public void doJob() {
		this.resetCronExpression();
		if(taskHandleChain == null || taskHandleChain.isEmpty()) {
			return;
		}
		for(TaskHandle taskHandle: taskHandleChain) {
			try {
				taskHandle.handleTask();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	} 
	
	public void resetCronExpression() {
		if(this.getScheduler() == null) {
			return;
		}
		try {
			CronTriggerBean trigger = (CronTriggerBean)this.getScheduler().getTrigger(this.getTriggerName(), Scheduler.DEFAULT_GROUP);
			String oldCronExpression = trigger.getCronExpression();
			String newCronExpression = this.getNewCronExpression();
			
			if(StringUtils.isNotBlank(oldCronExpression) && oldCronExpression.equalsIgnoreCase(newCronExpression)) {
				return;
			}
			
			trigger.setCronExpression(newCronExpression);
			scheduler.rescheduleJob(triggerName, Scheduler.DEFAULT_GROUP, trigger);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected String getNewCronExpression() {
		return "";
	}

	public Scheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	public List<TaskHandle> getTaskHandleChain() {
		return taskHandleChain;
	}

	public void setTaskHandleChain(List<TaskHandle> taskHandleChain) {
		this.taskHandleChain = taskHandleChain;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

}
