/**
 * 
 */
package me.power.speed.common.serialize.kryo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import me.power.speed.common.serialize.SerializeObject;

import org.objenesis.strategy.StdInstantiatorStrategy;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;

/**
 * @author xuehui.miao
 *
 */
//https://github.com/EsotericSoftware/kryo#io
//http://x-rip.iteye.com/blog/1555344
//http://blog.csdn.net/hengyunabc/article/details/7764509
//https://github.com/eishay/jvm-serializers/wiki/Staging-Results
public class KryoSerializeUtil {
	
	public static void serializeObject(String fileName) throws FileNotFoundException {
		Kryo kryo = new Kryo();
		kryo.setReferences(false);
		kryo.setRegistrationRequired(false);
		kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
		//kryo.register(SerializeObject.class, new JavaSerializer());
		
		Output output = new Output(10000);
		output.setOutputStream(new FileOutputStream(fileName));
		kryo.writeObject(output, SerializeObject.getSerializeObject());
		output.flush();
		output.close();
		System.out.println("ok");
	}
	
	public static void main(String[] args) {
		try {
			KryoSerializeUtil.serializeObject("f:\\file.bin");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
