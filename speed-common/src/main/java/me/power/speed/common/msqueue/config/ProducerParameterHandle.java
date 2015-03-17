package me.power.speed.common.msqueue.config;

import org.codehaus.jettison.json.JSONObject;
import me.power.speed.common.msqueue.MessageQueue;
import me.power.speed.common.msqueue.producer.KestrelProductParameter;
import me.power.speed.common.msqueue.producer.ProducerParameter;

public class ProducerParameterHandle extends ParameterHandle {
	private static final String MESSAGE_QUEUE_TYPE = "mqt";
	private static ProducerParameterHandle instance = new ProducerParameterHandle();
	
	private ProducerParameterHandle() {
		
	}
	
	public static ProducerParameterHandle getInstance() {
		return instance;
	}
	
	public ProducerParameter getProducerParameter(String configJson) {
		try {
			JSONObject json = new JSONObject(configJson);
			MessageQueue mq = MessageQueue.valueOf(json.getString(MESSAGE_QUEUE_TYPE));
			if(MessageQueue.kestrel == mq) {
				KestrelProductParameter kpp = KestrelProductParameter.buildKestrelProductParameter();
				this.setParameterValue(kpp, mq, json);
				System.out.println("ipAndPort:" + kpp.getIpAndPort());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
