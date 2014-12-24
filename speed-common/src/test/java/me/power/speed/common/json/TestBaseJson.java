package me.power.speed.common.json;

import java.io.IOException;
import java.io.StringWriter;

import net.sf.json.JSONObject;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.alisoft.nano.bench.Nano;

//https://code.google.com/p/nanobench/source/checkout

public class TestBaseJson extends AbstractJson {
	 
	 @Before
	 public void before() {
		 this.setJsonObject();
		 measurements =1;
		 threads = 100000000;
		 SerialTimes =1000;
	 }
	 
	 @Test
	 public void testFormatResult() {
		 try {
			 ObjectMapper mapper = new ObjectMapper();
			 String jacksonResult = mapper.writeValueAsString(jsonObj);
			 String jsonLibResult = JSONObject.fromObject(jsonObj).toString();
			 
			 Assert.assertNotNull(jacksonResult);
			 Assert.assertNotNull(jsonLibResult);
			 
			 System.out.println(jacksonResult);
			 System.out.println(jsonLibResult);
			 
			 Assert.assertEquals(jacksonResult, jsonLibResult);
			 
		} catch (Exception e) {
			this.fail(e);
		}
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
                        //StringWriter writer = null;

                        for (int i = 0; i < SerialTimes; i++) {
                            //writer = new StringWriter();
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
    
    //ͨ��json-lib���д���
    @Test
    public void testJsonLib() {
        Nano.bench().measurements(measurements).threads(threads).measure("[json-lib]",
                new Runnable() {
                    public void run() {
                        for (int i = 0; i < SerialTimes; i++) {
                            JSONObject.fromObject(jsonObj).toString();
                        }
                    }
                });
    }
}
