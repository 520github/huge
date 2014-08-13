package me.power.speed.common.outofmemory.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang.StringUtils;

public class MapSubListOutOfMemory {
	private static ConcurrentMap<String, List<Object>> dataMap = new ConcurrentHashMap<String, List<Object>>();
	//每5分钟输出一次缓存
	private static long perTimeOutput = 5*60*1000;
		
	public static void setDataMap(String key, Object value) {
		try {
			if(StringUtils.isBlank(key)) {
				return;
			}
			if(value == null) {
				return ;
			}
			List<Object> dataList = dataMap.get(key);
			if(dataList == null) {
				//dataList = new ArrayList<Object>();
				dataList = Collections.synchronizedList(new ArrayList<Object>());
				dataList.add(Long.valueOf(System.currentTimeMillis()));
			}
			if(dataList.size() > 20) {
				dataList.remove(1);
				dataList.add(value);
			}
			else {
				dataList.add(value);
			}
			dataMap.put(key, dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void clear(String key) {
		List<Object> list = dataMap.get(key);
		if(list != null && list.size() > 0) {
			list = list.subList(0, 1);
			dataMap.put(key, list);
		}
	}
	
	/**
	 * 判断是否到时间点输出
	 * @param sequencenumber
	 * @return
	 */
	public static boolean isTimeDue(String key) {
		List<Object> list = dataMap.get(key);
		if(list == null || list.size() < 1) {
			return false;
		}
		//上一次执行时间点
		long timeLong = 0;
		try {
			Object time = list.get(0);
			timeLong = (Long)time;
		} catch (Exception e) {
			e.printStackTrace();
		}
		//当前时间点
		long currentTime = System.currentTimeMillis();
		//比较时间间距
		if(currentTime - timeLong >= perTimeOutput) {
			list.set(0, Long.valueOf(currentTime));
			System.out.println(key +":can output currentTime: " + currentTime + ",lastTime:" + timeLong);
			return true;
		}
		return false;
	}
	
	public static void cycleHandle() {
		while(true) {
			for(int i=0; i< 800; i++) {
				try {
					String key = String.valueOf(i);
					setDataMap(key, BigDto.getBigDto());
					if(isTimeDue(key)) {
						clear(key);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static String getMes(int i) {
		String mes = "this is message ";
		return mes + i;
	}
	
	
	
	public static void waitBefore() {
		try {
			Thread.sleep(30000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		waitBefore();
		cycleHandle();
	}
}
