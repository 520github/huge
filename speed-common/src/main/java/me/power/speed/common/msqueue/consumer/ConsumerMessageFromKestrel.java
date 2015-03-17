package me.power.speed.common.msqueue.consumer;

import me.power.speed.common.msqueue.MemcacheClientBuilder;
import me.power.speed.common.msqueue.producer.ProducerMessageException;
import net.rubyeye.xmemcached.MemcachedClient;

public class ConsumerMessageFromKestrel extends AbstractConsumerMessage {
	private MemcachedClient client;
	private String ipAndPort;
	private String queueName;
	private int sleepTime;
	
	private void initParameter(KestrelConsumerParameter kcp) throws ProducerMessageException {
		this.ipAndPort = kcp.getIpAndPort();
		this.queueName = kcp.getQueueName();
		this.sleepTime = kcp.getSleepTime();
	}
	
	private void initMemcachedClient() throws ProducerMessageException {
		try {
			if(client ==null) {
				client = MemcacheClientBuilder.getCacheTemplate(this.ipAndPort);
			}
		} catch (Exception e) {
			throw new ProducerMessageException(e.getMessage(), e);
		}
	}
	
	public String onGetData(ConsumerParameter consumerParameter)
			throws ConsumerMessageException {
		try {
			this.initParameter(consumerParameter.getKcp());
			this.initMemcachedClient();
			String data = (String) client.get(queueName + "/t=" + sleepTime+ "/close/open");
			return data;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public void onConsumerData(ConsumerParameter consumerParameter)
			throws ConsumerMessageException {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
