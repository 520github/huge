/**
 * 
 */
package me.power.speed.common.serialize.fst;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.junit.Test;
import me.power.speed.AbstractBaseTest;
import me.power.speed.common.serialize.SerializeObject;

/**
 * @author xuehui.miao
 *
 */
public class TestFstSerializeUtil extends AbstractBaseTest {
	
	@Test
	public void testReadObjectFromStream() {
		try {
			InputStream stream = new FileInputStream("f:\\fst.bin");
			SerializeObject result = (SerializeObject)FstSerializeUtil.readObjectFromStream(stream, SerializeObject.class);
			this.print(result);
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	@Test
	public void testWriteObject2Stream() {
		try {
			OutputStream stream = new FileOutputStream("f:\\fst.bin");
			FstSerializeUtil.writeObject2Stream(stream, SerializeObject.getSerializeObject());
		} catch (Exception e) {
			this.fail(e);
		}
	}
}
