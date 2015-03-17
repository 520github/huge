package me.power.speed.common.msqueue.consumer.thread;

import java.util.concurrent.atomic.AtomicBoolean;

import me.power.speed.common.msqueue.consumer.ConsumerParameter;
import me.power.speed.common.msqueue.consumer.logic.ConsumerMessageLogic;

public abstract class AbstractConsumerMessageByThread implements
		ConsumerMessageByThread {
	private AtomicBoolean isRunning = new AtomicBoolean(true);
	protected ConsumerParameter consumerParameter;
	private ConsumerMessageLogic logic;
	
	public AbstractConsumerMessageByThread(ConsumerParameter consumerParameter, ConsumerMessageLogic logic) {
		this.consumerParameter = consumerParameter;
		this.logic = logic;
	}

	public void run() {
		while(isRunning.get()) {
			try {
				String data = this.getOneData();
				logic.consumerMessage(data);
				//this.consumeOneData(data);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	protected abstract void consumeOneData(String data);
	
	protected abstract String getOneData();

}
