/**
 * 
 */
package me.power.speed.common.stream;

import java.io.OutputStream;

/**
 * @author xuehui.miao
 *
 */
public class StreamUtil {
	
	public static void closeOutputStream(OutputStream outStream) {
		if(outStream == null) {
			return;
		}
		try {
			outStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
