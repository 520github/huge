package me.power.speed.common.msqueue.consumer;

public interface ConsumerMessage {
	public String getData(ConsumerParameter consumerParameter) throws ConsumerMessageException ;
	
	public void consumerData(ConsumerParameter consumerParameter) throws ConsumerMessageException ;
	
	
}
