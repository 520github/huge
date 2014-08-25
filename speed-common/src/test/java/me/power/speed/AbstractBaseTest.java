package me.power.speed;

import org.junit.Assert;

import me.power.speed.common.json.JacksonJsonUtil;
import me.power.speed.common.stream.file.FileUtil;

public class AbstractBaseTest {
	protected void print(Object obj) {
		try {
			if(obj == null) {
				return;
			}
			String json = JacksonJsonUtil.convertObject2Json(obj);
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(obj.toString());
		}
	}
	
	protected void fail(Exception e) {
		e.printStackTrace();
		Assert.fail(e.getMessage());
	}
	
	protected String getCurrentEncode() {
		return System.getProperty("file.encoding");
	}
	
	protected void write2File(String filePath, String message) {
		try {
			FileUtil.appendWrite2File(filePath, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
