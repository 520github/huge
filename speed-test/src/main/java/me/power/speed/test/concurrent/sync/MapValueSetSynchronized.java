package me.power.speed.test.concurrent.sync;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class MapValueSetSynchronized extends AbstractMapValueSynchronized {
	private static Map<String,Set<String>> map = new ConcurrentHashMap<String, Set<String>>();
	
	public static void putValue(String key, String offset) {
		if(!map.containsKey(key)) {
			Set<String> set = new ConcurrentSkipListSet<String>();
			set.add(offset);
			map.put(key, set);
		}
		
		Set<String> set = map.get(key);
		synchronized (set) {
			System.out.println("add offset-->" + offset);
			set.add(offset);
		}
		sleep(1);
	}
	
	public static void getAndClear(String key) {
		Set<String> set = map.get(key);
		synchronized (set) {
			printSet(set);
			set.clear();
			printSet(map.get(key));
			sleep(30000);
		}
	}
	
	private static void addValueByThread(final String key, final int start, final int end) {
		Thread thread = new Thread(new Runnable() {	
			public void run() {
				for(int i=start;i<end;i++) {
					putValue(key,String.valueOf(i));
				}
			}
		});
		thread.start();
	}
	
	protected static void testPutAndGetAndClearByThread() {
		final String key = "12307";
		addValueByThread(key, 1, 200);
		sleep(30);
		Thread td1 = new Thread(new Runnable() {
			public void run() {
				getAndClear(key);
			}
		});
		td1.start();
	}
	
	public static void main(String[] args) {
		testPutAndGetAndClearByThread();
	}
}
