package me.power.speed.common.msqueue.consumer;

public class KestrelConsumerParameter {
	private String ipAndPort;
	private String queueName;
	private int sleepTime;
	
	public static KestrelConsumerParameter bulidKestrelConsumerParameter() {
		return new KestrelConsumerParameter();
	}
	
	public String getIpAndPort() {
		return ipAndPort;
	}
	public KestrelConsumerParameter setIpAndPort(String ipAndPort) {
		this.ipAndPort = ipAndPort;
		return this;
	}
	public String getQueueName() {
		return queueName;
	}
	public KestrelConsumerParameter setQueueName(String queueName) {
		this.queueName = queueName;
		return this;
	}
	public int getSleepTime() {
		return sleepTime;
	}
	public KestrelConsumerParameter setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
		return this;
	}
}
