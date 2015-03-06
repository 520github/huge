package me.power.speed.test.concurrent.sync;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class MapValueSetSynchronized extends AbstractMapValueSynchronized {
	private static Map<String,Set<String>> map = new ConcurrentHashMap<String, Set<String>>();
	
	public static void putValue(String key, String offset) {
		if(!map.containsKey(key)) {
			synchronized (key) {
				System.out.println(""  + Thread.currentThread().getName() + "-->key " + key + " into to synchronized");
				if(map.containsKey(key)) {
					System.out.println(""  + Thread.currentThread().getName() + "-->key-->" + key + " is contains.....");
					map.get(key).add(offset);
					return;
				}
				
				Set<String> set = new ConcurrentSkipListSet<String>();
				set.add(offset);
				map.put(key, set);
			}
		}
		
		Set<String> set = map.get(key);
		synchronized (set) {
			System.out.println("add offset-->" + offset);
			set.add(offset);
		}
		//sleep(1);
	}
	
	public static void getAndClear(String key) {
		Set<String> set = map.get(key);
		synchronized (set) {
			printSet(set);
			Set<String> temp = new ConcurrentSkipListSet<String>(set);
			set.clear();
			System.out.println("come from map............");
			printSet(map.get(key));
			System.out.println("come from temp...........");
			printSet(temp);
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
		addValueByThread(key, 1, 200000);
		//addValueByThread("12221", 200000, 300000);
		sleep(30);
		Thread td1 = new Thread(new Runnable() {
			public void run() {
				getAndClear(key);
			}
		});
		td1.start();
	}
	
	protected static void testPutByThread() {
		String key = "12307";
		addValueByThread("12307", 1, 2000);
		addValueByThread(key, 2000, 3000);
		addValueByThread("12307", 3000, 4000);
	}
	
	public static void main(String[] args) {
		//testPutAndGetAndClearByThread();
		testPutByThread();
	}
}
