package me.power.speed.common.json;

import me.power.speed.entity.test.DataPackage;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	public static DataPackage getDataPackage(String content) {
		try {
			//忽略Class中没有的字段，如果不加下面这行代码，当出现没有的字段 就会报UnrecognizedPropertyException异常
			objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			return objectMapper.readValue(content,  DataPackage.class);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	
}
