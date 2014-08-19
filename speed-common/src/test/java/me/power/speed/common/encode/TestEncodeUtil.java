package me.power.speed.common.encode;

import org.junit.Test;

import me.power.speed.AbstractBaseTest;

public class TestEncodeUtil extends AbstractBaseTest {
	
	@Test
	public void testGetBytes() {
		try {
			byte bts[] = EncodeUtil.getBytes("жа","gbk");
			for(byte bt : bts) {
				String hex = Integer.toHexString(bt & 0xFF);
				String bin = Integer.toBinaryString(bt & 0xFF);
				
				this.print("byte:" + bt);
				this.print("hex:"+hex);
				this.print("bin:"+bin);
				
			}
			//this.print(this.getCurrentEncode());
		} catch (Exception e) {
			this.fail(e);
		}
	}
}
