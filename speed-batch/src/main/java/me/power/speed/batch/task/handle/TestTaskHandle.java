/**
 * 
 */
package me.power.speed.batch.task.handle;

import me.power.speed.batch.log.LogPortal;

/**
 * @author xuehui.miao
 *
 */
public class TestTaskHandle implements TaskHandle {

	/* (non-Javadoc)
	 * @see me.power.speed.batch.task.handle.TaskHandle#handleTask()
	 */
	public void handleTask() {
		LogPortal.getJobLog().logInfo("this is a test task handle.");
	}

}
