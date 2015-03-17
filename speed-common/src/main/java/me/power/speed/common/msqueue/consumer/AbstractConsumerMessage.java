package me.power.speed.common.msqueue.consumer;

public abstract class AbstractConsumerMessage implements ConsumerMessage {

	public String getData(ConsumerParameter consumerParameter)
			throws ConsumerMessageException {
		return null;
	}
	
	public abstract String onGetData(ConsumerParameter consumerParameter)
			throws ConsumerMessageException;

	public void consumerData(ConsumerParameter consumerParameter)
			throws ConsumerMessageException {
	}
	
	public abstract void onConsumerData(ConsumerParameter consumerParameter)
			throws ConsumerMessageException;

}
