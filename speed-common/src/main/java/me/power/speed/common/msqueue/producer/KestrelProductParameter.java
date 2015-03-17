package me.power.speed.common.msqueue.producer;

public class KestrelProductParameter {
	private String ipAndPort;
	private String queueName;
	private int expire = 0;
	
	private KestrelProductParameter() {
	}
	
	public static KestrelProductParameter buildKestrelProductParameter() {
		return new KestrelProductParameter();
	}
	
	public String getIpAndPort() {
		return ipAndPort;
	}
	public KestrelProductParameter setIpAndPort(String ipAndPort) {
		this.ipAndPort = ipAndPort;
		return this;
	}
	public String getQueueName() {
		return queueName;
	}
	public KestrelProductParameter setQueueName(String queueName) {
		this.queueName = queueName;
		return this;
	}
	public int getExpire() {
		return expire;
	}
	public KestrelProductParameter setExpire(int expire) {
		this.expire = expire;
		return this;
	}
	
}
