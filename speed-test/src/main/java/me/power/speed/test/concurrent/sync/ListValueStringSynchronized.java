package me.power.speed.test.concurrent.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListValueStringSynchronized {
	private static List<String> list = new CopyOnWriteArrayList<String>();
	
	private static int maxSize = 100;
	
	protected static void sleep(int sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void removeLastValue() {
		synchronized (list) {
			System.out.println("start remove last value");
			sleep(20000);
			int i = list.size()-1;
			System.out.println("start remove last value:" + list.get(i));
			list.remove(i);
			System.out.println("end remove last value");
		}
	}
	
	public static void getLastValue() {
		//synchronized (list) {
			System.out.println("start get last value");
			String value = list.get(list.size()-1);
			System.out.println("get last value:" + value);
		//}
	}
	
	private static void addValue(String value) {
		if(list.size() >= maxSize) {
			System.out.println("go into threadName:" + Thread.currentThread().getName()+ ",value:" + value);
			synchronized (list) {
				List<String> data = new ArrayList<String>(list);
				System.out.println(Thread.currentThread().getName()+ " value:" + value + ",dataSize:" + data.size());
				list.clear();
				printList(data);
			}
		}
		list.add(value);
	}
	
	private static void addValueByThread(final int start, final int end) {
		Thread thread = new Thread(new Runnable() {	
			public void run() {
				for(int i=start;i<end;i++) {
					addValue(String.valueOf(i));
				}
			}
		});
		thread.start();
//		try {
//			//thread.join();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	private static void printList(List<String> list) {
		if(list == null) {
			return;
		}
		System.out.println("print list size "+list.size()+" by " + Thread.currentThread().getName());
		for(String data: list) {
			System.out.println(data);
		}
	}
	
	protected static void testAddValueByThread() {
		addValueByThread(1,100);
		addValueByThread(200,302);
		while(true) {
			if(Thread.getAllStackTraces().size() ==5) {
				printList(list);
				break;
			}
		}
	}
	
	protected static void testRemoveAndGetLastValueByThread() {
		addValueByThread(1,100);
		sleep(20);
		Thread td1 = new Thread(new Runnable() {
			public void run() {
				removeLastValue();
			}
		});
		td1.start();
		
		sleep(20);
		
		Thread td2 = new Thread(new Runnable() {
			public void run() {
				getLastValue();
			}
		});
		td2.start();
	}
	
	public static void main(String[] args) {
		//testAddValueByThread();		
		testRemoveAndGetLastValueByThread();
	}
}
