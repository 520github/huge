package me.power.speed.common.json;

import me.power.speed.entity.test.DataPackage;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	public static DataPackage getDataPackage(String content) {
		try {
			//����Class��û�е��ֶΣ���������������д��룬������û�е��ֶ� �ͻᱨUnrecognizedPropertyException�쳣
			objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			return objectMapper.readValue(content,  DataPackage.class);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	
}
