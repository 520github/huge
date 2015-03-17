package me.power.speed.common.msqueue;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import me.power.speed.AbstractBaseTest;
import me.power.speed.common.msqueue.consumer.ConsumerParameter;
import me.power.speed.common.msqueue.consumer.KestrelConsumerParameter;
import me.power.speed.common.msqueue.consumer.logic.ConsumerMessageLogic;
import me.power.speed.common.msqueue.consumer.logic.DefaultConsumerMessageLogic;
import me.power.speed.common.msqueue.consumer.thread.ConsumerMessageByThread;
import me.power.speed.common.msqueue.consumer.thread.ConsumerMessageByThreadFromKestrel;
import me.power.speed.common.msqueue.producer.KestrelProducerMessage;
import me.power.speed.common.msqueue.producer.KestrelProductParameter;
import me.power.speed.common.msqueue.producer.ProducerMessage;
import me.power.speed.common.msqueue.producer.ProducerParameter;

public class TestProducerAndConsumer extends AbstractBaseTest {
	private ProducerMessage producer;
	private ProducerParameter pp;
	private ConsumerMessageByThread consumer;
	private ConsumerParameter cp;
	private ConsumerMessageLogic logic;
	
	
	@Before
	public void before() {
		producer = new KestrelProducerMessage();
		pp = new ProducerParameter();
		pp.setData("hello, ")
		.setKestrelProductParameter(KestrelProductParameter
				.buildKestrelProductParameter()
				.setIpAndPort("10.10.32.102:11212")
				.setQueueName("datapackage")
				.setExpire(0));
		
		cp = new ConsumerParameter();
		cp.setKcp(KestrelConsumerParameter.bulidKestrelConsumerParameter()
				.setIpAndPort("10.10.32.102:11212")
				.setQueueName("datapackage")
				.setSleepTime(10));
		
		logic = new DefaultConsumerMessageLogic();
		consumer = new ConsumerMessageByThreadFromKestrel(cp, logic);
	}
	
	@Test
	public void testProductAndConsumer() {
		try {
			Thread thread = new Thread(consumer);
			thread.start();
			while(true) {
				producer.producerMessage(pp.setData("hello," +UUID.randomUUID().toString()));
				this.sleep(100);
			}
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	
	public static void main(String[] args) {
		try {
			TestProducerAndConsumer test = new TestProducerAndConsumer();
			test.before();
			test.testProductAndConsumer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
