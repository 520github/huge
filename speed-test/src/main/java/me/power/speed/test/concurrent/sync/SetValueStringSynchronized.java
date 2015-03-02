package me.power.speed.test.concurrent.sync;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class SetValueStringSynchronized {
	private static Set<String> set = new ConcurrentSkipListSet<String>();
	
	public static void putSetValue() {
		set.add("");
		set.clear();
	}
	
	
	
}
