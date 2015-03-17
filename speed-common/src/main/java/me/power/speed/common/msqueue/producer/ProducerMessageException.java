package me.power.speed.common.msqueue.producer;

public class ProducerMessageException extends Exception {
	
	private static final long serialVersionUID = -3056439691317898020L;
	
	
	public ProducerMessageException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
