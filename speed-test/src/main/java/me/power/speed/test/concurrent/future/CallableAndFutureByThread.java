package me.power.speed.test.concurrent.future;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableAndFutureByThread extends AbstractCallableAndFuture {
	public static void callableAndFuture() {
		try {
			FutureTask<Integer> future = new FutureTask<Integer>(getCallable());
			Thread thread = new Thread(future);
			thread.start();
			
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
