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
	 private static int measurements = 100;//��������
	 private static int threads = 10;//�߳���
	 private static int SerialTimes = 10000;//ÿ���߳�ִ�����л�����
	 
	 @Before
	 public void before() {
		 this.setJsonObject();
	 }
	 
	 /*
	     * �ڻ�������ObjectMapper�������ʹ��jackson����json���л�
	     */
	    @Test
	    public void testJacksonWithCache() {
	        Nano.bench().measurements(measurements).threads(threads).measure(
	                "[jackson������ObjectMapper��]", new Runnable() {
	                    public void run() {
	                        // ObjectMapper���÷�ʽ
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
	     * �ڲ���������ObjectMapper�������ʹ��jackson����json���л�
	     */
	    @Test
	    public void testJacksonWithoutCache() {
	        Nano.bench().measurements(measurements).threads(threads).measure(
	                "[jackson��������ObjectMapper��]", new Runnable() {
	                    public void run() {
	                        StringWriter writer = null;

	                        for (int i = 0; i < SerialTimes; i++) {
	                            // ObjectMapper�����÷�ʽ
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
