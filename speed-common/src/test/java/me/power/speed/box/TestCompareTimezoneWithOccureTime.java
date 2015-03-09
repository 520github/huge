/**
 * 
 */
package me.power.speed.box;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.junit.Before;
import org.junit.Test;

import me.power.speed.AbstractBaseTest;
import me.power.speed.common.json.JacksonJsonUtil;
import me.power.speed.common.stream.file.FileHandleAndFilter;
import me.power.speed.common.stream.file.FileUtil;
import me.power.speed.common.util.IpUtil;

/**
 * @author xuehui.miao
 *
 */
public class TestCompareTimezoneWithOccureTime extends AbstractBaseTest {
	private String rootPath = "C:\\xuehui\\30-work\\10-datafilter\\10-data\\";
	private String filePath = "";
	private String resulPath;
	private String dataPackage;
	private JsonNode rootJson;
	private int count = 0;
	
	@Before
	public void before() {
		this.filePath = rootPath + "temp\\df01-2014-10-24-13-notz8.top1000.log";
		this.resulPath = rootPath + "compareTimezone_" + this.getCurrentTimeFileName()+ ".log";
	}
	
	@Test
	public void testCompareTimezoneWithOccureTime() {
		try {
			FileUtil.readLineDataFromFile(filePath, 1, new FileHandleAndFilter() {
				public void handleOneLineData(String data) {
					handleOneData(data);
				}
				public boolean filterOneLineData(String data) {
					if(StringUtils.isBlank(data)) {
						return true;
					}
					return false;
				}
			});
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	private void handleOneData(String data) {
		try {
			count++;
			boolean isCountrySame = false;
			boolean isTimezoneMatch = true;
			this.print("handle data count:" + count);
			dataPackage = data.substring(data.indexOf("{"));
			rootJson = JacksonJsonUtil.getJsonNode(dataPackage);
			
			StringBuffer result = new StringBuffer();
			String handleDataTime = this.getHandleDataTime(data);
			long receiveTime = this.getReceiveTime();
			result.append("handleDataTime-->").append(handleDataTime).append(";receiceTime-->").append(this.getTimeString(receiveTime)).append("(").append(receiveTime).append(")\n");
			result.append("country-->").append(this.getCountry()).append(";language-->").append(this.getLanguage()).append(";timezone-->").append(this.getTimezone()).append("\n");
			String ip = IpUtil.getMatchIps(this.getIp()).get(0);
			String ipCountry = this.getCountryCodeFromIp(ip);
			result.append("ip-->").append(ip).append(";ipCountry-->").append(ipCountry);
			if(StringUtils.isNotBlank(ipCountry) && ipCountry.equalsIgnoreCase(this.getCountry())) {
				result.append(";ip country same");
				isCountrySame = true;
			}
			result.append("\n");
			
			ArrayNode eventNodes = this.getEventArrayNode();
			for(int i=0; i<eventNodes.size(); i++) {
				JsonNode eventNode = eventNodes.get(i);
				String eventId = this.getEventId(eventNode);
				result.append("eventId-->").append(eventId);
				long occureTime = this.getEventOccurTime(eventNode);
				result.append(";occureTime-->").append(this.getTimeString(occureTime));
				result.append(";receive - occue = ").append(this.getMinBetweenReceiveAndOccureTime(receiveTime, occureTime)).append(" min");
				if(this.isMatchTimezoneWithOccureTime(receiveTime, occureTime)) {
					result.append(";occureTime is match timezone " + this.getTimezone());
				}
				else {
					if(
					  !"G4".equalsIgnoreCase(eventId) 
					  && !"G14".equalsIgnoreCase(eventId)) {
						isTimezoneMatch = false;
					}
				}
				if(this.isLastEvent(occureTime, receiveTime)) {
					result.append(";receive more than occure 5 min-->true");
				}
				result.append("\n");
			}
			
			if(isCountrySame && isTimezoneMatch && eventNodes.size()>0) {
				result.append("this is my found data \n");
			}
			
			result.append("\n");
			this.write2File(result.toString());
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	private void write2File(String message) {
		this.write2File(this.resulPath,message);
	}
	
	private JsonNode getDeviceJsonNode() {
		return rootJson.path("deviceProfile");
	}
	
	private long getReceiveTime() {
		return rootJson.path("receiveTime").getLongValue();
	}
	
	private int getTimezone() {
		return this.getDeviceJsonNode().path("timezone").getIntValue();
	}
	
	private int getDiffencetTimezoneWithChina() {
		return this.getTimezone() - 8;
	}
	
	private boolean isMatchTimezoneWithOccureTime(long receiveTime, long occureTime) {
		long min = this.getMinBetweenReceiveAndOccureTime(receiveTime, occureTime);
		int diffTimezoneHour = this.getDiffencetTimezoneWithChina();
		int diffMin = diffTimezoneHour * 60;
		
		long hour = Math.abs(min/60);
		
		if(Math.abs(diffTimezoneHour) >= 1 && Math.abs(min) <= 30) {
			return false;
		}
		
		if(Math.abs(hour - Math.abs(diffTimezoneHour)) > 1) {
			return false;
		}
		
		if(diffMin > 0) {
			if(min < 0 && Math.abs(min) >= diffMin) {
				return true;
			}
		}
		else if(diffMin <= 0) {
			if(min >=0 && min >= Math.abs(diffMin)) {
				return true;
			}
		}
		return false;
	}
	
	private long getMinBetweenReceiveAndOccureTime(long receiveTime, long occureTime) {
		return (receiveTime - occureTime)/(1000*60);
	}
	
	private boolean isLastEvent(long occureTime, long receiveTime) {
		if(getMinBetweenReceiveAndOccureTime(receiveTime, occureTime) >=5 ) {
			return true;
		}
		return false;
	}
	
	private String getCountry() {
		return this.getDeviceJsonNode().path("country").getTextValue();
	}
	
	private String getIp() {
		return this.getDeviceJsonNode().path("ip").getTextValue();
	}
	
	private String getLanguage() {
		return this.getDeviceJsonNode().path("language").getTextValue();
	}
	
	private ArrayNode getEventArrayNode() {
		JsonNode eventsNode = rootJson.path("events");
		if(eventsNode.isArray()) {
			return (ArrayNode)eventsNode;
		}
		return null;
	}
	
	private String getEventId(JsonNode eventNode) {
		return eventNode.path("eventID").getTextValue();
	}
	
	private long getEventOccurTime(JsonNode eventNode) {
		return eventNode.path("eventOccurTime").getLongValue();
	}
	
	private String getHandleDataTime(String data) {
		if(data.startsWith("{")) {
			return "not found handleData time";
		}
		return data.substring(0, data.indexOf("["));
	}
}
