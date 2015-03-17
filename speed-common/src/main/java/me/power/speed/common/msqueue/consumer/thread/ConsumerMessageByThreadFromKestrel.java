package me.power.speed.common.msqueue.consumer.thread;

import net.rubyeye.xmemcached.MemcachedClient;
import me.power.speed.common.msqueue.MemcacheClientBuilder;
import me.power.speed.common.msqueue.consumer.ConsumerParameter;
import me.power.speed.common.msqueue.consumer.KestrelConsumerParameter;
import me.power.speed.common.msqueue.consumer.logic.ConsumerMessageLogic;

public class ConsumerMessageByThreadFromKestrel extends
		AbstractConsumerMessageByThread {
	
	private MemcachedClient client;
	private String ipAndPort;
	private String queueName;
	private int sleepTime;
	
	public ConsumerMessageByThreadFromKestrel(ConsumerParameter consumerParameter, ConsumerMessageLogic logic) {
		super(consumerParameter, logic);
		this.initParameter(consumerParameter.getKcp());
		this.initMemcachedClient();
	}
	
	private void initParameter(KestrelConsumerParameter kcp) {
		this.ipAndPort = kcp.getIpAndPort();
		this.queueName = kcp.getQueueName();
		this.sleepTime = kcp.getSleepTime();
	}
	
	private void initMemcachedClient() {
		try {
			if(client ==null) {
				client = MemcacheClientBuilder.getCacheTemplate(this.ipAndPort);
			}
		} catch (Exception e) {
			//throw new ProducerMessageException(e.getMessage(), e);
		}
	}
	
	protected void consumeOneData(String data) {
		// TODO Auto-generated method stub

	}

	protected String getOneData() {
		try {
			return (String) client.get(queueName + "/t=" + sleepTime+ "/close/open");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
