/**
 * 
 */
package me.power.speed.test.thirdparty.chronicle.queue;

import net.openhft.chronicle.Chronicle;
import net.openhft.chronicle.tools.ChronicleTools;

import org.junit.Before;
import org.junit.Test;

import me.power.speed.test.AbstractTest;

/**
 * @author xuehui.miao
 *
 */
public class TestChronicleQueueSample1 extends AbstractTest {
	
	private Chronicle chronicle;
	
	@Before
	public void before() {
		try {
			String basePath = ChronicleQueueSample1.getBasePath("Simple1");
			//ChronicleTools.deleteOnExit(basePath);
			chronicle = ChronicleQueueSample1.getChronicleQueueBuilder(basePath);
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	@Test
	public void testChronicleQueueSample1() {
		try {
			String basePath = ChronicleQueueSample1.getBasePath("Simple1");
			chronicle = ChronicleQueueSample1.getChronicleQueueBuilder(basePath);
			ChronicleQueueSample1.writeObject2ChronicleQueue(chronicle, "hello my name 1");
			Object result = ChronicleQueueSample1.readObjectFromChronicleQueue(chronicle);
			this.print(result.toString());
			
			//result = ChronicleQueueSample1.readObjectFromChronicleQueue(chronicle);
			//this.print(result.toString());
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	@Test
	public void testWriteChronicleQueueSample1() {
		try {
			ChronicleQueueSample1.writeObject2ChronicleQueue(chronicle, "hello my name 1");
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	@Test
	public void testReadChronicleQueueSample1() {
		try {
			Object result = ChronicleQueueSample1.readObjectFromChronicleQueue(chronicle);
			this.print(result.toString());
		} catch (Exception e) {
			this.fail(e);
		}
	}
}
