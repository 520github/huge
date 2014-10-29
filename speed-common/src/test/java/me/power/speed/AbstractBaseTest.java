package me.power.speed;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;

import com.tendcloud.iplocation.thrift.IpLocation;

import me.power.speed.common.json.JacksonJsonUtil;
import me.power.speed.common.location.ip.IpLocCltThreadLocal;
import me.power.speed.common.stream.file.FileUtil;

public class AbstractBaseTest {
	
	protected Date getCurrentDate() {
		return new Date();
	}
	
	protected Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
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
	
	protected String getTimeString(long time) {
		Timestamp ts = new Timestamp(time);
		return ts.toString();
	}
	
	protected String getCurrentTimeFileName() {
		return this.getTimestampString().replaceAll(" ", "-").replaceAll(":", "-");
	}
	
	protected String getTimestampString() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss SSS");
		return df.format(new Timestamp(System.currentTimeMillis()));
	}
	
	protected String getCountryCodeFromIp(String ip) {
		if(StringUtils.isBlank(ip)) {
			return "ip is empty";
		}
		IpLocation location = this.getLocationFromIp(ip);
		if(location != null) {
			return location.getCountry();
		}
		return "country is empty";
	}
	
	protected IpLocation getLocationFromIp(String ip) {
		try {
			return IpLocCltThreadLocal.getClient().getLocationFromIp(ip);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new IpLocation("unknow", "unknow", "unknow");
	}
}
