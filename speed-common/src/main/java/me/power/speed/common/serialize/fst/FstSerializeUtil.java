/**
 * 
 */
package me.power.speed.common.serialize.fst;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import de.ruedigermoeller.serialization.FSTConfiguration;
import de.ruedigermoeller.serialization.FSTObjectInput;
import de.ruedigermoeller.serialization.FSTObjectOutput;

/**
 * @author xuehui.miao
 *
 */
//http://www.oschina.net/p/fst
public class FstSerializeUtil {
	private static FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();
	
	public static Object readObjectFromStream(InputStream stream, Class cla) throws Exception {
		FSTObjectInput in = conf.getObjectInput(stream);
		Object result = in.readObject(cla);
		stream.close();
		return result;
	}
	
	public static void writeObject2Stream(OutputStream stream, Object value) throws IOException {
		FSTObjectOutput out = conf.getObjectOutput(stream);
		out.writeObject( value, value.getClass());
		out.flush();
		stream.close();
	}
}
