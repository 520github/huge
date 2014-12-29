package me.power.speed.common.util;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import org.junit.Test;

import me.power.speed.AbstractBaseTest;

public class TestSet extends AbstractBaseTest {
	
	@Test
	public void testConcurrentSkipListSet() {
		Set<Integer> set = new ConcurrentSkipListSet<Integer>();
		set.add(10);
		set.add(3);
		set.add(5);
		set.add(100);
		for(Integer value : set) {
			this.print(value);
		}
	}
}
