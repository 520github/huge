/**
 * 
 */
package me.power.speed.batch.task.timerjob;

import me.power.speed.batch.task.handle.TestTaskHandle;

/**
 * @author xuehui.miao
 *
 */
public class TestDynamicTimeJob extends AbstractDynamicTimerJob {
	
	public TestDynamicTimeJob() {
		this.addTaskHandle(new TestTaskHandle());
	}
	
	public void doJob() {
		System.out.println("start TestDynamicTimeJob......");
		super.doJob();
	}
}
