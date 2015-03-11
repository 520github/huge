package me.power.speed.test.concurrent.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableAndFutureByExecutor extends AbstractCallableAndFuture {
	
	public static void callableAndFuture() {
		try {
			ExecutorService threadPool = Executors.newSingleThreadExecutor();
			Future<Integer> future = threadPool.submit(getCallable());
			
			Thread.sleep(5000);
			
			System.out.println(future.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		callableAndFuture();
	}
}
