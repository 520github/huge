package me.power.speed.common.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.power.speed.AbstractTest;

public class AbstractJson extends AbstractTest {
	protected static JsonObject jsonObj;
	
	protected void setJsonObject() {
		String stringValue = "²âÊÔ×Ö·û´®";
        int intValue = 123456;
        double doubleValue = 123456.134456;
        long longValue = 1234567890L;
        boolean booleanValue = true;
        Map<String, Object> mapValue = new HashMap<String, Object>();
        List<Object> listValue = new ArrayList<Object>();

        mapValue.put("key1", "value1");
        mapValue.put("key2", "value2");
        mapValue.put("key3", new JsonObject("a string", 123, 123.123, 123L, false,
                new HashMap<String, Object>(), new ArrayList<Object>()));

        listValue.add("value1");
        listValue.add("value2");
        listValue.add(mapValue.get("key3"));
        
        jsonObj = new JsonObject(stringValue, intValue, doubleValue, longValue,
                booleanValue, mapValue, listValue);
	}
}
