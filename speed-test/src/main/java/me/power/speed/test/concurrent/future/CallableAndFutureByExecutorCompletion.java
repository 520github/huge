package me.power.speed.test.concurrent.future;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallableAndFutureByExecutorCompletion extends
		AbstractCallableAndFuture {
	
	public static void callableAndFuture() {
		try {
			ExecutorService threadPool = Executors.newCachedThreadPool();
			CompletionService<Integer> cs = new ExecutorCompletionService<Integer>(threadPool);
			
			for(int i=0;i<5;i++) {
				cs.submit(getCallable(i));
			}
			
			for(int i=0;i<5;i++) {
				System.out.println(cs.take().get());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		callableAndFuture();
	}
}
