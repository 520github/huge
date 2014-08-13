package me.power.speed.common.encrypt.md5;


import java.io.IOException;
import java.io.StringWriter;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import com.alisoft.nano.bench.Nano;

import me.power.speed.AbstractTest;

public class TestMd5Util extends AbstractTest {
	private String md5 = "1234567890";
	@Test
	public void testMd5() {
		String str = "12345";
		
		String result1 = Md5Util.md5ByApache(str);
		String result2 = Md5Util.md5ByJdk(str);
		
		Assert.assertEquals(result1, result2);
		
		System.out.println("result:" + result1);
	}
	
	@Test
	public void testMd5ByJdk() {
		threads = 1;
		Nano.bench().measurements(measurements).threads(threads).measure(
                "[Md5ByJdk]", new Runnable() {
                    public void run() {
                        for (int i = 0; i < SerialTimes; i++) {
                            try {
								Md5Util.md5ByJdk(md5);
							} catch (Exception e) {
								e.printStackTrace();
							}
                        }
                    }
              });
	}
	
	@Test
	public void testMd5ByApache() {
		threads = 1;
		Nano.bench().measurements(measurements).threads(threads).measure(
                "[Md5ByApache]", new Runnable() {
                    public void run() {
                        for (int i = 0; i < SerialTimes; i++) {
                            try {
								Md5Util.md5ByApache(md5);
							} catch (Exception e) {
								e.printStackTrace();
							}
                        }
                    }
              });
	}
}
