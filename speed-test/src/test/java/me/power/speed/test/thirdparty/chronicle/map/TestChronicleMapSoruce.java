/**
 * 
 */
package me.power.speed.test.thirdparty.chronicle.map;

import java.util.Map;
import java.util.Set;

import net.openhft.chronicle.map.ChronicleMap;

import org.junit.Before;
import org.junit.Test;

/**
 * @author xuehui.miao
 *
 */
public class TestChronicleMapSoruce extends TestCompareDiffCache {
	@Before
	public void before() {
		cycleNum = 1000;
	}
	
	@Test
	public void testSetToChronicleMapByFilePath() {
		try {
			ChronicleMap<String,Set> map = this.getSetChronicleMapSourceByFilePath(path+"set"+cycleNum);
			this.print("before map size:" + map.size());
			this.sleep(2000);
			this.setSetValueToMap(map);
			this.showMapSizeAndValue(map);
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	@Test
	public void testShowMapSizeAndValue() {
		try {
			ChronicleMap<String,Set> map = this.getSetChronicleMapSourceByFilePath(path+"set"+cycleNum);
			this.showMapSizeAndValue(map);
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	private void setSetValueToMap(ChronicleMap<String,Set> map) {
		for(int i=0; i<cycleNum; i++) {
			String key = String.valueOf(i);
			Set<Integer> obj = map.get(key);
			if(obj == null) {
				obj = this.getNewSet();
				for(int j=0;j<cycleNum;j++) {
					int random = (int)(Math.random()*10000000);
					//this.print("random:" + random);
					obj.add(random);
				}
				map.put(key, obj);
			}
			else {
				this.print("is exit key: " + key);
				for(int j=0;j<10;j++) {
					int random = (int)(Math.random()*10000000);
					//this.print("random:" + random);
					obj.add(random);
				}
				map.put(key, obj);
			}
		}
	}
	
	private void showMapSizeAndValue(ChronicleMap<String,Set> map) {
		for(String key: map.keySet()) {
			Set<Integer> set = map.get(key);
			if(set == null) {
				this.print(key + "---> the value is empty." );
				continue;
			}
			this.print(key + "--->" + set.size());
			StringBuffer values = new StringBuffer();
			for(Integer value: set) {
				values.append(value).append(",");
			}
			this.print(key + "-->" + values.toString());
		}
		this.print("map size:" + map.size());
	}
}
