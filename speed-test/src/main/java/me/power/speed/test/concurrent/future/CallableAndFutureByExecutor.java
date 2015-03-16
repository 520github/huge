package me.power.speed.test.concurrent.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CallableAndFutureByExecutor extends AbstractCallableAndFuture {
	private static ExecutorService threadPool = Executors.newCachedThreadPool();
			//Executors.newSingleThreadExecutor();
	
	public static void callableAndFuture() {
		try {
			Future<Integer> future = threadPool.submit(getCallable());
			
			Thread.sleep(5000);
			
			System.out.println(future.get(5, TimeUnit.SECONDS));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		callableAndFuture();
	}
}
