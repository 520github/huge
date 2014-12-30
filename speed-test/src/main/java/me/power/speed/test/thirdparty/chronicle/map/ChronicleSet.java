package me.power.speed.test.thirdparty.chronicle.map;

import java.util.Set;

import net.openhft.chronicle.set.ChronicleSetBuilder;

public class ChronicleSet {
	private static Set<Integer> set = ChronicleSetBuilder.of(Integer.class).create();
	
	public static void put(Integer value) {
		set.add(value);
	}

	public static Set<Integer> getSet() {
		return set;
	}
	
}
