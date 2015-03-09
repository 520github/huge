package me.power.speed.box;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import me.power.speed.AbstractBaseTest;
import me.power.speed.ConsumerTime;
import me.power.speed.common.regular.JdkRegularUtil;
import me.power.speed.common.stream.file.FileHandleAndFilter;
import me.power.speed.common.stream.file.FileUtil;
//http://wgslucky.blog.163.com/blog/static/97562532201332324639689/
public class TestParseRequestHeaderLog extends AbstractBaseTest {
	private String rootFilePath = "F:\\data\\df\\thread\\header\\";
	private String filePath;
	private String pattern;
	
	private Map<String,List<String>> headerKeyValueMap = null;
	private Map<Goal,Long> goalMap = null;
	
	public static enum Goal {
		sum,
		max,
		min,
		avg
	}
	
	@Before
	public void before() {
		this.filePath = rootFilePath+ "headeraa";
		//this.filePath = rootFilePath+ "header.log";
		this.pattern = "\\[[-/\\w\\.\\,]+\\]:\\[[-/\\w\\.\\,]+\\];";
		headerKeyValueMap = new HashMap<String, List<String>>();
		goalMap = new HashMap<Goal, Long>();
	}
	
	@Test
	public void parseRequestHeaderLog() {
		try {
			ConsumerTime ct = new ConsumerTime();
			FileUtil.readLineDataFromFile(filePath, 50, new FileHandleAndFilter() {
				public void handleOneLineData(String data) {
					//writeData2File(data);
					handleOneData(data);
				}
				
				public boolean filterOneLineData(String data) {
					return false;
				}
			});
			ct.endConsumeTime();
			this.printGoalResult();
			this.printHeaderKeyValueResult();
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	protected void writeData2File(String data) {
		try {
			System.out.println(data);
			FileUtil.appendWrite2File(rootFilePath + "header.log", data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void printGoalResult() {
		String filePath = rootFilePath + "goal.log";
		for(Goal goal : goalMap.keySet()) {
			long value = goalMap.get(goal);
			//System.out.println(goal + ":" + value);
			this.write2File(filePath, goal + ":" + value);
		}
	}
	
	private void printHeaderKeyValueResult() {
		String filePath = rootFilePath + "result.log";
		System.out.println("headerKeySize:" + headerKeyValueMap.size());
		for(String key : headerKeyValueMap.keySet()) {
			List<String> values = headerKeyValueMap.get(key);
			System.out.println("["+key+"]:[" + values.get(0) + "]");
			this.write2File(filePath, key + " of value size:" + values.size());
			
			StringBuffer vls = new StringBuffer();
			for(String value : values) {
				vls.append(value + ",");
			}
			this.write2File(filePath, vls.toString());
		}
	}
	
	private void handleOneData(String data) {
		List<String> groupList = JdkRegularUtil.getGroupListByPattern(data, pattern);
		for(String group: groupList) {
			this.storeHeaderKeyAndValue(group);
		}
		this.storeGoal(groupList.size());
	}
	
	private void storeGoal(long length) {
		for(Goal goal : Goal.values()) {
			long value = this.getGoalValue(goal, length);
			goalMap.put(goal, value);
		}
	}
	
	private long getGoalValue(Goal goal, long length) {
		if(!goalMap.containsKey(goal)) {
			if(goal == Goal.sum) {
				return 1;
			}
			return length;
		}
		if(goalMap.containsKey(goal)) {
			long value = goalMap.get(goal);
			if(goal == Goal.sum) {
				System.out.println("sum:" + value);
				return 1 + value;
			}
			if(goal == Goal.max) {
				return value > length ? value : length;
			}
			if(goal == Goal.min) {
				return value < length ? value : length;
			}
			if(goal == Goal.avg) {
				return (value + length)/2;
			}
		}
		return 0;
	}
	
	private void storeHeaderKeyAndValue(String group) {
		String keyValue[] = this.getGroupKeyValue(group);
		String key = keyValue[0];
		String value = keyValue[1];
		if(headerKeyValueMap.containsKey(key)) {
			List<String> values = headerKeyValueMap.get(key);
			if(!values.contains(value)) {
				values.add(value);
				headerKeyValueMap.put(key, values);
			}
		}
		else {
			List<String> values =  new ArrayList<String>();
			values.add(value);
			headerKeyValueMap.put(key, values);
		}
	}
	
	//[X-Real-IP]:[219.133.15.229];
	private String[] getGroupKeyValue(String group) {
		group = group.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(";", "");
		return group.split(":");
	}
}
