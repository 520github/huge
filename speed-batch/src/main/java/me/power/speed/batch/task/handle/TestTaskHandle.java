/**
 * 
 */
package me.power.speed.batch.task.handle;

/**
 * @author xuehui.miao
 *
 */
public class TestTaskHandle implements TaskHandle {

	/* (non-Javadoc)
	 * @see me.power.speed.batch.task.handle.TaskHandle#handleTask()
	 */
	public void handleTask() {
		System.out.println("this is a test task handle.");
	}

}
