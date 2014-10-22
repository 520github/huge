/**
 * 
 */
package me.power.speed.common.json;

import org.codehaus.jackson.JsonNode;
import org.junit.Before;
import org.junit.Test;

/**
 * @author test
 *
 */
public class TestJacksonJsonUtil extends AbstractJson {
	String filePath;
	String jsonData;
	
	@Before
	public void before() {
		filePath = "C:\\xuehui\\30-work\\10-dataf\\10-data\\big\\big_40_source.log";
		jsonData = this.readFileFirstLineData(filePath);
		jsonData = jsonData.substring(jsonData.indexOf("{"));
	}
	
	@Test
	public void testRemoveJsonNodeFiled() {
		try {
			JsonNode jsonNode = JacksonJsonUtil.getJsonNode(jsonData);
			JacksonJsonUtil.removeJsonNodeFiled(jsonNode, "networks");
			
			this.print(jsonNode.toString());
			
		} catch (Exception e) {
			this.fail(e);
		}
		
	}
}
