package me.power.speed.common.outofmemory.list;

public class ThreadMapSubListOutOfMemory extends Thread {
	public void run() {
		MapSubListOutOfMemory.cycleHandle();
	}
	
	public static void main(String[] args) {
		try {
			MapSubListOutOfMemory.waitBefore();
			for (int i = 0; i < 3; i++) {
				ThreadMapSubListOutOfMemory obj = new ThreadMapSubListOutOfMemory();
				obj.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
