package me.power.speed.box;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import me.power.speed.AbstractBaseTest;
import me.power.speed.common.regular.JdkRegularUtil;
import me.power.speed.common.stream.file.FileHandleAndFilter;
import me.power.speed.common.stream.file.FileUtil;

public class TestParseRequestHeaderLog extends AbstractBaseTest {
	private String filePath;
	private String pattern;
	
	@Before
	public void before() {
		this.filePath = "F:\\data\\df\\thread\\datafilter\\reqHeaderLog-1010321058081.log.2014-08-14";
		this.pattern = "\\[[-/\\w\\.\\,]+\\]:\\[[-/\\w\\.\\,]+\\];";
	}
	
	@Test
	public void parseRequestHeaderLog() {
		try {
			FileUtil.readLineDataFromFile(filePath, new FileHandleAndFilter() {
				@Override
				public void handleOneLineData(String data) {
					handleOneData(data);
				}
				
				@Override
				public boolean filterOneLineData(String data) {
					return false;
				}
			});
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	private void handleOneData(String data) {
		List<String> groupList = JdkRegularUtil.getGroupListByPattern(data, pattern);
		for(String group: groupList) {
			System.out.println(group);
		}
	}
}
