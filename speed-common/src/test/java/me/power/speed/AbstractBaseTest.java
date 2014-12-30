package me.power.speed;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;

import com.tendcloud.iplocation.thrift.IpLocation;

import me.power.speed.common.json.JacksonJsonUtil;
import me.power.speed.common.location.ip.IpLocCltThreadLocal;
import me.power.speed.common.stream.file.FileUtil;
import me.power.speed.ConsumerTime.ConsumerTimeHandle;

public class AbstractBaseTest {
	protected static int measurements = 100;//��������
	protected static int threads = 10;//�߳���
	protected static int SerialTimes = 10000;//ÿ���߳�ִ�����л�����
	
	protected void handleWithConsumerTime(ConsumerTimeHandle handle) {
		ConsumerTime ct = new ConsumerTime();
		handle.handle();
		ct.endConsumeTime();
	}
	
	protected Date getCurrentDate() {
		return new Date();
	}
	
	protected Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public void print(Object obj) {
		this.print(obj, true);
	}
	
	protected void print(Object obj, boolean isPrint) {
		try {
			if(!isPrint) {
				return;
			}
			
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
	
	protected List<Integer> getRandomIntegerList(int cycleNum, int baseNum) {
		ConsumerTime ct = new ConsumerTime();
		List<Integer> resultList = new ArrayList<Integer>();
		for(int i=0;i<cycleNum;i++) {
			double d = Math.random();
			int result = (int)(d*d*d*baseNum);
			this.print(result, true);
			resultList.add(result);
		}
		ct.endConsumeTime();
		this.print("random size:" + resultList.size(), true);
		return resultList;
	}
	
	protected int[] getRandomIntArrays(int cycleNum, int baseNum) {
		ConsumerTime ct = new ConsumerTime();
		int resultArrays[] = new int[cycleNum];
		for(int i=0;i<cycleNum;i++) {
			double d = Math.random();
			int result = (int)(d*d*d*baseNum);
			this.print(result, false);
			resultArrays[i] = result;
		}
		ct.endConsumeTime();
		this.print("random int size:" + resultArrays.length, true);
		return resultArrays;
	}
	
	protected int[] getRandomRangeIntArrays(int cycleNum, int baseNum) {
		ConsumerTime ct = new ConsumerTime();
		int resultArrays[] = new int[cycleNum];
		for(int i=0;i<cycleNum;i++) {
			double d = Math.random()+1;
			int result = (int)(d*baseNum);
			this.print(result, false);
			resultArrays[i] = result;
		}
		ct.endConsumeTime();
		this.print("random int size:" + resultArrays.length, true);
		return resultArrays;
	}
	
	protected int[] getSameIntArrays(int cycleNum, int value) {
		ConsumerTime ct = new ConsumerTime();
		int resultArrays[] = new int[cycleNum];
		for(int i=0;i<cycleNum;i++) {
			this.print(value, true);
			resultArrays[i] = value;
		}
		ct.endConsumeTime();
		this.print("same int size:" + resultArrays.length, true);
		return resultArrays;
	}
	
	protected int[] getTwoIncreamIntArrays(int cycleNum, int start, int step) {
		int[] results = new int[cycleNum*2];
		int[] rs1 = this.getIncreamIntArrays(cycleNum, start, step);
		for(int i=0;i<cycleNum;i++) {
			results[i] = rs1[i];
		}
		int[] rs2 = this.getIncreamIntArrays(cycleNum, start, step);
		for(int i=0;i<cycleNum;i++) {
			results[cycleNum+i] = rs2[i];
		}
		
		this.print("two incream int size:" + results.length, true);
		return results;
	}
	
	protected int[] getIncreamIntArrays(int cycleNum, int start, int step) {
		ConsumerTime ct = new ConsumerTime();
		int resultArrays[] = new int[cycleNum];
		for(int i=0;i<cycleNum;i++) {
			int result = start;
			start = start + step;
			this.print(result, false);
			resultArrays[i] = result;
		}
		ct.endConsumeTime();
		this.print("incream int size:" + resultArrays.length, true);
		return resultArrays;
	}
	
	protected int[] getDecreamIntArrays(int cycleNum, int start, int step) {
		ConsumerTime ct = new ConsumerTime();
		int resultArrays[] = new int[cycleNum];
		int count =0;
		for(int i=cycleNum-1;i>=0;i--) {
			int result = i * step +start;
			this.print(result, true);
			resultArrays[count] = result;
			count++;
		}
		ct.endConsumeTime();
		this.print("incream int size:" + resultArrays.length, true);
		return resultArrays;
	}
}
