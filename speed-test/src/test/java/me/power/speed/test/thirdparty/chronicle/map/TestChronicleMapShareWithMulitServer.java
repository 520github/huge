package me.power.speed.test.thirdparty.chronicle.map;

import junit.framework.Assert;

import org.junit.Test;

import me.power.speed.test.AbstractTest;

public class TestChronicleMapShareWithMulitServer extends AbstractTest {
	
	@Test
	public void testChronicleMapShareWithMulitServer() {
		try {
			Integer key = 109;
			String value = "share with mulit server";
			ChronicleMapShareWithMulitServer.getMap2().put(key, value);
			
			int i =0;
			for(; i<5000; i++) {
				if(ChronicleMapShareWithMulitServer.getMap1()
						.equals(ChronicleMapShareWithMulitServer.getMap2())) {
					this.print(String.valueOf(i));
					break;
				}
				Thread.sleep(1);
			}
			
			this.print(ChronicleMapShareWithMulitServer.getMap1().get(key));
			
			Assert.assertEquals(ChronicleMapShareWithMulitServer.getMap1(), ChronicleMapShareWithMulitServer.getMap2());
			
		} catch (Exception e) {
			this.fail(e);
		}
	}
}
