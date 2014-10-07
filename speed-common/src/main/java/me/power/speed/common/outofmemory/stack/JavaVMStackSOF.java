/**
 * 
 */
package me.power.speed.common.outofmemory.stack;

/**
 * @author xuehui.miao
 *
 */
public class JavaVMStackSOF {
	private int length = 1;
	
	public void stackLeak() {
		length++;
		stackLeak();
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		JavaVMStackSOF jvss = new JavaVMStackSOF();
		try {
			jvss.stackLeak();
		} catch (Exception e) {
			System.out.println("length:  " + jvss.length);
			throw e;
		}
	}

}
