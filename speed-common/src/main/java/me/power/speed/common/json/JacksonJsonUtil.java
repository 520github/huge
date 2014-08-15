/**
 * 
 */
package me.power.speed.common.json;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @author xuehui.miao
 *
 */
public class JacksonJsonUtil {
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static String convertObject2Json(Object obj) throws JsonGenerationException, JsonMappingException, IOException {
		return mapper.writeValueAsString(obj);
	}
}
