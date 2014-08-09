package me.power.speed.common.json;

import java.io.IOException;
import java.io.StringWriter;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import com.alisoft.nano.bench.Nano;

public class TestBaseJson extends AbstractJson {
	 private static int measurements = 100;//测量次数
	 private static int threads = 10;//线程数
	 private static int SerialTimes = 10000;//每个线程执行序列化次数
	 
	 @Before
	 public void before() {
		 this.setJsonObject();
	 }
	 
	 /*
	     * 在缓存重用ObjectMapper的情况下使用jackson进行json序列化
	     */
	    @Test
	    public void testJacksonWithCache() {
	        Nano.bench().measurements(measurements).threads(threads).measure(
	                "[jackson（重用ObjectMapper）]", new Runnable() {
	                    public void run() {
	                        // ObjectMapper重用方式
	                        ObjectMapper mapper = new ObjectMapper();
	                        StringWriter writer = null;

	                        for (int i = 0; i < SerialTimes; i++) {
	                            writer = new StringWriter();
	                            try {
	                                //mapper.writeValue(writer, jsonObj);
	                                mapper.writeValueAsString(jsonObj);
	                            } catch (JsonGenerationException e) {
	                                e.printStackTrace();
	                            } catch (JsonMappingException e) {
	                                e.printStackTrace();
	                            } catch (IOException e) {
	                                e.printStackTrace();
	                            }
	                        }
	                    }
	               });
	    }
	    
	    /*
	     * 在不缓存重用ObjectMapper的情况下使用jackson进行json序列化
	     */
	    @Test
	    public void testJacksonWithoutCache() {
	        Nano.bench().measurements(measurements).threads(threads).measure(
	                "[jackson（不重用ObjectMapper）]", new Runnable() {
	                    public void run() {
	                        StringWriter writer = null;

	                        for (int i = 0; i < SerialTimes; i++) {
	                            // ObjectMapper非重用方式
	                            ObjectMapper mapper = new ObjectMapper();
	                            writer = new StringWriter();
	                            try {
	                                //mapper.writeValue(writer, jsonObj);
	                                mapper.writeValueAsString(jsonObj);
	                            } catch (JsonGenerationException e) {
	                                e.printStackTrace();
	                            } catch (JsonMappingException e) {
	                                e.printStackTrace();
	                            } catch (IOException e) {
	                                e.printStackTrace();
	                            }
	                        }
	                    }
	                });
	    }
}
