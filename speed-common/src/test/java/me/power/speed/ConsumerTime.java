package me.power.speed;

public class ConsumerTime {
	private long startTime;
	private long endTime;
	
	public ConsumerTime() {
		this.startTime = System.currentTimeMillis();
	}
	
	public void endConsumeTime() {
		this.endTime = System.currentTimeMillis();
		long consume = this.endTime - this.startTime;
		
		System.out.println("consume ms: " + consume);
		System.out.println("consume m: " + consume/1000);
		System.out.println("consume min: " + consume/1000/60);
	}
	
	public interface ConsumerTimeHandle {
		public void handle();
	}
}
