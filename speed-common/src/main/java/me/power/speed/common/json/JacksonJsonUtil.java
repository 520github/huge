/**
 * 
 */
package me.power.speed.common.json;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.node.ArrayNode;

/**
 * @author xuehui.miao
 *
 */
public class JacksonJsonUtil {
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static String convertObject2Json(Object obj) throws JsonGenerationException, JsonMappingException, IOException {
		return mapper.writeValueAsString(obj);
	}
	
	public static JsonNode getJsonNode(String jsonData) throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(jsonData, JsonNode.class);
	}
	
	public static void removeJsonNodeFiled(JsonNode jsonNode, String filed) {
		if(jsonNode == null) {
			return;
		}
		JsonNode deviceNode = jsonNode.path("deviceProfile");
		JsonNode filedNode = deviceNode.path(filed);
		if(filedNode != null && filedNode.isArray()) {
			System.out.println("--------------------remove filed: " + filed);
			((ObjectNode)deviceNode).remove(filed);
		}
		
		JsonNode eventsNode = jsonNode.path("events");
		if(eventsNode.isArray()) {
			ArrayNode eventsArrayNode =  (ArrayNode)eventsNode;
			for(int i=0;i<eventsArrayNode.size();i++) {
				JsonNode eventNode = eventsArrayNode.get(i);
				
				if(isInnerCustomEvent(eventNode)) {
					eventsArrayNode.remove(i);
					i--;
					continue;
				}
				
				JsonNode eventFiledNode = eventNode.path(filed);
				
				if(eventFiledNode != null && eventFiledNode.isArray()) {
					System.out.println("---------------remove event filed: " + filed);
					((ObjectNode)eventNode).remove(filed);
				}
			}
			
		}
	}
	
	public static boolean isInnerCustomEvent(JsonNode eventJson) {
		if(eventJson == null) {
			return false;
		}
		String eventId = eventJson.path("eventID").getTextValue();
		if("G8".equalsIgnoreCase(eventId)) {
			String actionId = eventJson.path("eventData").path("actionID").getTextValue();
			if(actionId != null && actionId.startsWith("__tx")) {
				System.out.println("--------------find inner G8.");
				return true;
			}
		}
		return false;
	}
}
