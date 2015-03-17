package me.power.speed.common.msqueue.config;

import org.junit.Before;
import org.junit.Test;

import me.power.speed.AbstractBaseTest;

public class TestProducerParameterHandle extends AbstractBaseTest {
	private ProducerParameterHandle pph;
	private String configJson;
	
	@Before
	public void before() {
		pph = ProducerParameterHandle.getInstance();
		configJson = "{mqt:kestrel,ipAndPort:\"10.10.32.109:11212\"}";
	}
	
	@Test
	public void testGetProducerParameter() {
		try {
			pph.getProducerParameter(configJson);
		} catch (Exception e) {
			this.fail(e);
		}
	}
}
