package me.power.speed.common.msqueue.producer;

public abstract class AbatractProducerMessage implements ProducerMessage {

	public final boolean producerMessage(ProducerParameter producerParameter) {
		try {
			return this.onProducerMessage(producerParameter);
		} catch (Exception e) {
			return this.onProducerMessageException(producerParameter, e);
		}
	}
	
	protected abstract boolean onProducerMessage(ProducerParameter producerParameter) throws ProducerMessageException;
	
	
	protected abstract boolean onProducerMessageException(ProducerParameter producerParameter, Exception e);

}
