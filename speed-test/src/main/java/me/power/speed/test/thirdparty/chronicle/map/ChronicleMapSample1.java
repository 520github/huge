/**
 * 
 */
package me.power.speed.test.thirdparty.chronicle.map;

import java.util.Map;
import net.openhft.chronicle.map.ChronicleMapBuilder;

/**
 * @author xuehui.miao
 *
 */
public class ChronicleMapSample1 {
	private static Map<Integer, CharSequence> map = ChronicleMapBuilder.of(Integer.class, CharSequence.class).create();
	
	public static void put(Integer key, String value) {
		map.put(key, value);
	}
	
	public static String get(Integer key) {
		return (String)map.get(key);
	}
}
