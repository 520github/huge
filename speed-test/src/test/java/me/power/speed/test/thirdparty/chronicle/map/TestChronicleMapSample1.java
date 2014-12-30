/**
 * 
 */
package me.power.speed.test.thirdparty.chronicle.map;

import org.junit.Test;

import me.power.speed.test.AbstractTest;

/**
 * @author xuehui.miao
 *
 */
public class TestChronicleMapSample1 extends AbstractTest {
	
	@Test
	public void testChronicleMapSample1() {
		Integer key = 10;
		String value = "like like";
		ChronicleMapSample1.put(key, value);
		this.print(ChronicleMapSample1.get(key));
	}
}
