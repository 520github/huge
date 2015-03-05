package me.power.speed.test.concurrent.sync;

import java.util.Set;

public abstract class AbstractMapValueSynchronized {
	protected static void printSet(Set set) {
		if(set == null || set.size() < 1) {
			System.out.println("set is empty");
			return ;
		}
		System.out.println("set size:" + set.size());
		for(Object obj: set) {
			System.out.println(obj.toString());
		}
	}
	
	protected static void sleep(int sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
