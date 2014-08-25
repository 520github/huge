package me.power.speed.common.msqueue.kestrel;

import me.power.speed.common.msqueue.MemcacheClientBuilder;
import net.rubyeye.xmemcached.MemcachedClient;

public class KestrelMessageQueue {
	private MemcachedClient client;
	private String ip = "10.10.32.102:11212";
	private String queueName = "datapackage";
	private String sleepTime =  "10";
	
	public KestrelMessageQueue() {
		
	}
	
	public KestrelMessageQueue(String ip, String queueName) {
		this.ip = ip;
		this.queueName = queueName;
	}
	
	public String getMessage() {
		String message = null;
		try {
			if(client == null) {
				client = MemcacheClientBuilder.getCacheTemplate(ip);
			}
			message = (String) client.get(queueName + "/t=" + sleepTime + "/close/open");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}
}
