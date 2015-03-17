package me.power.speed.common.msqueue.producer;

public class RocketMqProducerMessage extends AbatractProducerMessage {

	protected boolean onProducerMessage(ProducerParameter producerParameter)
			throws ProducerMessageException {
		return false;
	}

	protected boolean onProducerMessageException(
			ProducerParameter producerParameter, Exception e) {
		return false;
	}

}
