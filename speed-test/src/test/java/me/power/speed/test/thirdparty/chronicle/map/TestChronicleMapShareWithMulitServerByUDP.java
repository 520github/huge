package me.power.speed.test.thirdparty.chronicle.map;

import junit.framework.Assert;

import org.junit.Test;

import me.power.speed.test.AbstractTest;

public class TestChronicleMapShareWithMulitServerByUDP extends AbstractTest {
	@Test
	public void testChronicleMapShareWithMulitServerByUDP() {
		try {
			Integer key = 119;
			String value = "share with mulit server by udp.";
			ChronicleMapShareWithMulitServerByUDP.getMap2().put(key, value);
			
			int i =0;
			for(; i<5000; i++) {
				if(ChronicleMapShareWithMulitServerByUDP.getMap1()
						.equals(ChronicleMapShareWithMulitServerByUDP.getMap2())) {
					this.print(String.valueOf(i));
					break;
				}
				Thread.sleep(1);
			}
			
			this.print(ChronicleMapShareWithMulitServerByUDP.getMap1().get(key));
			
			Assert.assertEquals(ChronicleMapShareWithMulitServerByUDP.getMap1(), ChronicleMapShareWithMulitServerByUDP.getMap2());
			
		} catch (Exception e) {
			this.fail(e);
		}
	}
}
