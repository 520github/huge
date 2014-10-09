/**
 * 
 */
package me.power.speed.common.stream;

import java.io.OutputStream;
import java.io.Writer;

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
	
	public static void closeWrite(Writer writer) {
		if(writer == null) {
			return;
		}
		try {
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
