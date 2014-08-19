package me.power.speed.box;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import me.power.speed.AbstractBaseTest;
import me.power.speed.ConsumerTime;
import me.power.speed.common.stream.file.FileHandleAndFilter;
import me.power.speed.common.stream.file.FileUtil;

public class TestParseRequestHeaderResult extends AbstractBaseTest {
	private String rootFilePath = "F:\\data\\df\\thread\\header\\";
	private String keyFlag = "of value size";
	private String key;
	private String keyValue;
	private int keyValueNum;
	private int maxKeyValueLength = 100;
	private String filePath;
	//private LinkedHashMap<String, String> resultMap = new LinkedHashMap<String, String>();
	private LinkedList<String> resultList = new LinkedList<String>();
	
	@Before
	public void before() {
		this.filePath = rootFilePath+ "result.log";
	}
	
	@Test
	public void testSplite() {
		String value = "no-cache,yes-cache,No-Cache,";
		String values[] =value.split(",");
		this.print(values.length);
	}
	
	
	@Test
	public void parseRequestHeaderResult() {
		try {
			ConsumerTime ct = new ConsumerTime();
			FileUtil.readLineDataFromFile(filePath, 50, new FileHandleAndFilter() {
				@Override
				public void handleOneLineData(String data) {
					handleOneData(data);
				}
				
				@Override
				public boolean filterOneLineData(String data) {
					return false;
				}
			});
			ct.endConsumeTime();
			this.printResult();
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	private void printResult() {
		String filePath = rootFilePath + "result_result.log";
		int i = 1;
		for(String data : resultList) {
			this.write2File(filePath, i+"> "+data);
			i++;
		}
	}
	
	private void handleOneData(String data) {
		if(StringUtils.isBlank(data)) {
			return;
		}
		int index = data.indexOf(keyFlag);
		//key
		if(index > -1) {
			key = data.substring(0, index);
			return;
		}
		
		//value
		String datas[] = data.split(",");
		keyValueNum = datas.length;
		keyValue = datas[0];
		if(keyValueNum ==1 && keyValue.length() > maxKeyValueLength) {
			keyValue = "value length " + keyValue.length() + ",is too long and skip.";
			resultList.addLast(key+":"+keyValue);
		}
		else {
			resultList.addFirst(key+":"+keyValue);
		}
	}
}
