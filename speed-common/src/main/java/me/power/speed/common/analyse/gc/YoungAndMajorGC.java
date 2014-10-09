/**
 * 
 */
package me.power.speed.common.analyse.gc;

/**
 * @author xuehui.miao
 *
 */
public class YoungAndMajorGC {
	
	private static final int _1MB = 1024 * 1024;
	
	public static void testYoungAndMajorGC() {
		byte[] data1, data2, data3, data4 = null;
		data1 = new byte[2 * _1MB];
		data2 = new byte[2 * _1MB];
		data3 = new byte[2 * _1MB];
		data4 = new byte[4 * _1MB];
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		YoungAndMajorGC.testYoungAndMajorGC();
	}

}
