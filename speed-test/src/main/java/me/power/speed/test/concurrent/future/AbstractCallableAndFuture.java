package me.power.speed.test.concurrent.future;

import java.util.Random;
import java.util.concurrent.Callable;

public abstract class AbstractCallableAndFuture {
	protected static Callable<Integer> getCallable() {
		Callable<Integer> callable = new Callable<Integer>() {	
			public Integer call() throws Exception {
				return new Random().nextInt(1000);
			}
		};
		return callable;
	}
	
	protected static Callable<Integer> getCallable(final int id) {
		Callable<Integer> callable = new Callable<Integer>() {	
			public Integer call() throws Exception {
				//return new Random().nextInt(1000) ;
				return id;
			}
		};
		return callable;
	}
}
