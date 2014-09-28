package me.power.speed.common.encode;

import org.junit.Test;

import me.power.speed.AbstractBaseTest;

public class TestEncodeUtil extends AbstractBaseTest {
	
	@Test
	public void testGetBytes() {
		try {
			byte bts[] = EncodeUtil.getBytes("��","gbk");
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
	
	@Test
	public void testEmptyString() {
		String str = " ";
		byte bytes[] = str.getBytes();
		System.out.println(bytes.length);
	}
	
	@Test
	public void testFilter4BitCharset() {
		String replace = "𭺗";
		String source = "水晶-𭺗arrior Armour";
		this.print(source);
		source = source.replace(replace, "");
		this.print(source);
		
		//String result = EncodeUtil.filter4BitCharset("水晶-𭺗arrior Armour");
		//this.print(result);
	}
}
