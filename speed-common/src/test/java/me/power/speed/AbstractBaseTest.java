package me.power.speed;

import java.util.Date;

import org.junit.Assert;

import me.power.speed.common.json.JacksonJsonUtil;
import me.power.speed.common.stream.file.FileUtil;

public class AbstractBaseTest {
	
	protected Date getCurrentDate() {
		return new Date();
	}
	
	protected void print(Object obj) {
		try {
			if(obj == null) {
				return;
			}
			
			if(obj.getClass().isArray()) {
				this.printArrayValue(obj);
				return;
			}
			
			if(obj instanceof String) {
				System.out.println(obj.toString());
				return;
			}
			
			String json = JacksonJsonUtil.convertObject2Json(obj);
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(obj.toString());
		}
	}
	
	protected void printArrayValue(Object obj) {
		if(obj instanceof String[]) {
			String values[] = (String[])obj;
			for(String value : values) {
				this.print(value);
			}
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
	
	protected String readFileFirstLineData(String filePath) {
		try {
			return FileUtil.readFirstLineDataFromFile(filePath);
		} catch (Exception e) {
			this.fail(e);
		}
		return null;
	}
}
