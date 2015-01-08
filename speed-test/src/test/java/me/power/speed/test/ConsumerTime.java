package me.power.speed.test;

public class ConsumerTime {
	protected static boolean isPrintTime = true;
	protected static boolean isMoreTime = false;
	public interface ConsumerTimeHandle {
		public void handle();
	}
	
	private long startTime;
	private long endTime;
	
	public ConsumerTime() {
		this.startTime = System.currentTimeMillis();
	}
	
	public void endConsumeTime() {
		this.endTime = System.currentTimeMillis();
		long consume = this.endTime - this.startTime;
		
		if(!isPrintTime) {
			return;
		}
		
		System.out.println("consume ms: " + consume);
		if(isMoreTime) {
			System.out.println("consume m: " + consume/1000);
			System.out.println("consume min: " + consume/1000/60);
		}
	}
}
