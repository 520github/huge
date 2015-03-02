package me.power.speed.test.concurrent.sync;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapValueStringSynchronized extends AbstractMapValueSynchronized {
	private static Map<String, String> map = new ConcurrentHashMap<String, String>();
	
	public static void setMapValueWithSynchronized(String key, String value) {
		synchronized (map) {
			try {
				System.out.println("key:" + key);
				map.put(key, value);
				Thread.sleep(10000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void setMapValue(String key, String value) {
		map.put(key, value);
		map.clear();
	}
	
	public static void main(String[] args) {
		try {
			Thread t1 = new Thread(new Runnable() {
				public void run() {
					for(int i=0; i<10000;i++) {
						setMapValueWithSynchronized(String.valueOf(i),String.valueOf(i));
//						try {
//							Thread.sleep(2);
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
					}
				}
			});
			t1.start();
			
			//Thread.sleep(1000);
			
			Thread t2 = new Thread(new Runnable() {
				public void run() {
					for(int i=20000;i<23000;i++) {
						setMapValue(String.valueOf(i),String.valueOf(i));
					}
					System.out.println("ok,map size:" + map.size());
//					for(String key: map.keySet()) {
//						System.out.println(key);
//					}
				}
			});
			t2.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
