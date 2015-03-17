package me.power.speed.common.msqueue.producer;

import me.power.speed.common.msqueue.MemcacheClientBuilder;
import net.rubyeye.xmemcached.MemcachedClient;

public class KestrelProducerMessage extends AbatractProducerMessage {
	private MemcachedClient client;
	private String ipAndPort;
	private String queueName;
	private int expire;
	
	private void initParameter(KestrelProductParameter kpp) throws ProducerMessageException {
		this.ipAndPort = kpp.getIpAndPort();
		this.queueName = kpp.getQueueName();
		this.expire = kpp.getExpire();
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
	
	protected boolean onProducerMessage(ProducerParameter producerParameter) throws ProducerMessageException {
		try {
			this.initParameter(producerParameter.getKestrelProductParameter());
			this.initMemcachedClient();
			return client.set(this.queueName, this.expire, producerParameter.getData());
		} catch (Exception e) {
			throw new ProducerMessageException(e.getMessage(), e);
		}
	}

	protected boolean onProducerMessageException(
			ProducerParameter producerParameter, Exception e) {
		return false;
	}

}
