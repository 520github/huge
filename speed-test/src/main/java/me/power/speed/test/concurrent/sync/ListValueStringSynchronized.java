package me.power.speed.test.concurrent.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListValueStringSynchronized {
	private static List<String> list = new CopyOnWriteArrayList<String>();
	
	private static int maxSize = 100;
	
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
	}
	
	public static void main(String[] args) {
		testAddValueByThread();
		//System.out.println(Thread.getAllStackTraces().size());
		while(true) {
			if(Thread.getAllStackTraces().size() ==5) {
				printList(list);
				break;
			}
		}		
	}
}
