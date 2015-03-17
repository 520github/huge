package me.power.speed.common.msqueue.config;

import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jettison.json.JSONObject;

import me.power.speed.common.msqueue.MessageQueue;

public class ParameterHandle {
	
	protected void setParameterValue(Object obj, MessageQueue mq, JSONObject json) {
		String columnNames = mq.getParameterColumnName();
		String columnNamesArrays[] = columnNames.split(",");
		for(String columnName : columnNamesArrays) {
			try {
				String value = json.getString(columnName);
				System.out.println(columnName +"-->" + value);
				BeanUtils.setProperty(obj, columnName, value);
				System.out.println("result:" + BeanUtils.getProperty(obj, columnName));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
