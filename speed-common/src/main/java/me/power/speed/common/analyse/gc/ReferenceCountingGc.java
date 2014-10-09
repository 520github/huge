/**
 * 
 */
package me.power.speed.common.analyse.gc;

/**
 * @author xuehui.miao
 *
 */
public class ReferenceCountingGc {
	public Object instance = null;
	
	private static final int _1MB = 1024 * 1024;
	
	private byte[] bigSize = new byte[2 * _1MB];
	
	public static void referenceEach() {
		ReferenceCountingGc objA = new ReferenceCountingGc();
		ReferenceCountingGc objB = new ReferenceCountingGc();
		
		objA.instance = objB;
		objB.instance = objA;
		
		objA = null;
		objB = null;
		
		System.gc();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReferenceCountingGc.referenceEach();
	}

}
