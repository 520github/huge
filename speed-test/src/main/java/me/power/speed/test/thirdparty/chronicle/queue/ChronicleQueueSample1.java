/**
 * 
 */
package me.power.speed.test.thirdparty.chronicle.queue;

import java.io.IOException;

import net.openhft.chronicle.Chronicle;
import net.openhft.chronicle.ChronicleQueueBuilder;
import net.openhft.chronicle.ExcerptAppender;
import net.openhft.chronicle.ExcerptTailer;
import net.openhft.chronicle.tools.ChronicleTools;

/**
 * @author xuehui.miao
 *
 */
public class ChronicleQueueSample1 {
	
	public static String getBasePath(String category) {
		return System.getProperty("java.io.tmpdir") + "/" + category;
	}
	
	public static Chronicle getChronicleQueueBuilder(String basePath) throws IOException {
		System.out.println("basePath:" + basePath);
		//ChronicleTools.deleteOnExit(basePath);
		return ChronicleQueueBuilder.indexed(basePath).build();
	}
	
	public static void writeObject2ChronicleQueue(Chronicle chronicle, Object object) throws IOException {
		ExcerptAppender appender = chronicle.createAppender();
		appender.startExcerpt(256);
		appender.writeObject(object);
		appender.finish();
		appender.close();
	}
	
	public static Object readObjectFromChronicleQueue(Chronicle chronicle) throws IOException {
		ExcerptTailer reader = chronicle.createTailer();
		reader.index(0);
		System.out.println("length:" + reader.length());
//		while(reader.nextIndex()) {
//			
//		}
		Object obj = reader.readObject();
		reader.finish();
		reader.close();
		
		return obj;
	}
}
