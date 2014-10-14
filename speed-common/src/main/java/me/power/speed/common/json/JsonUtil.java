package me.power.speed.common.json;

import me.power.speed.entity.test.DataPackage;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jettison.json.JSONObject;

import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonUtil {
	private static com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
	
	public static DataPackage getDataPackage(String content) {
		try {
			//����Class��û�е��ֶΣ������������д��룬������û�е��ֶ� �ͻᱨUnrecognizedPropertyException�쳣
			objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			return objectMapper.readValue(content,  DataPackage.class);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	
	public static String writeBeanAsJsonString(Object obj) throws Exception {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationConfig.Feature.WRITE_NULL_PROPERTIES, false);
			//mapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public static Object readStringAsBean(String value, Class type) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationConfig.Feature.WRITE_NULL_PROPERTIES, false);
		mapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(value, type);
	}
	
	public static JSONObject getJsonObjectByBean(Object obj) throws Exception {
		if(obj == null) {
			throw new NullPointerException();
		}
		String jsonStr;
		if(obj instanceof String) {
			jsonStr = obj.toString();
		}
		else {
			jsonStr = writeBeanAsJsonString(obj);
		}
		JSONObject jsonObject = new JSONObject(jsonStr);
		System.out.println(jsonObject.toString());
		return jsonObject;
	}
	
}
