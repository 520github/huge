/**
 * 
 */
package me.power.speed.common.outofmemory.stack;

/**
 * @author xuehui.miao
 *
 */
public class JavaVMStackOOM {
	
	private void dontStop() {
		while(true) {
			
		}
	}
	
	public void stackLeakByThread() {
		while(true) {
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					dontStop();
				}
			});
			thread.start();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JavaVMStackOOM jvso = new JavaVMStackOOM();
		jvso.stackLeakByThread();
	}

}
