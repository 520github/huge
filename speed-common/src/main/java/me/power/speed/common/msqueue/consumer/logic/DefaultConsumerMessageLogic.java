package me.power.speed.common.msqueue.consumer.logic;

public class DefaultConsumerMessageLogic implements ConsumerMessageLogic {

	public void consumerMessage(String data) {
		if(data == null) {
			return;
		}
		System.out.println("data:" + data);
	}

}
