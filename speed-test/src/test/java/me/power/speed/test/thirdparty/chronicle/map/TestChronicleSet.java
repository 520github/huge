package me.power.speed.test.thirdparty.chronicle.map;

import org.junit.Test;

import me.power.speed.test.AbstractTest;

public class TestChronicleSet extends AbstractTest {
	@Test
	public void testChronicleSet() {
		ChronicleSet.put(123);
		ChronicleSet.put(3789);
		ChronicleSet.put(209);
		
		for(Integer value: ChronicleSet.getSet()) {
			this.print(String.valueOf(value));
		}
	}
}
