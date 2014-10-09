/**
 * 
 */
package me.power.speed.common.serialize.kryo;

import org.junit.Test;

import me.power.speed.AbstractBaseTest;

/**
 * @author xuehui.miao
 *
 */
public class TestKryoSerializeUtil extends AbstractBaseTest {
	
	@Test
	public void serializeObject() {
		String fileName = "f:\\serialize.log";
		try {
			KryoSerializeUtil.serializeObject(fileName);
		} catch (Exception e) {
			this.fail(e);
		}
		
	}

}
