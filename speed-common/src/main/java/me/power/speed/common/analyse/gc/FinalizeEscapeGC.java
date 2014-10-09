/**
 * 
 */
package me.power.speed.common.analyse.gc;

/**
 * 执行gc之后，进行自我拯救的方法
 * @author xuehui.miao
 *
 */
public class FinalizeEscapeGC {
	public static FinalizeEscapeGC SAVE_HOOK = null;
	
	public void isAlive() {
		System.out.println("yes, i am still alive:");
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("finalize method executed!");
		FinalizeEscapeGC.SAVE_HOOK = this;
	}

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		SAVE_HOOK = new FinalizeEscapeGC();
		
		SAVE_HOOK = null;
		System.gc();
		Thread.sleep(500);
		
		if(SAVE_HOOK != null) {
			SAVE_HOOK.isAlive();
		}
		else {
			System.out.println("no, i am dead now.");
		}
		
		SAVE_HOOK = null;
		System.gc();
		Thread.sleep(500);
		if(SAVE_HOOK != null) {
			SAVE_HOOK.isAlive();
		}
		else {
			System.out.println("no, i am dead again.");
		}
	}

}
