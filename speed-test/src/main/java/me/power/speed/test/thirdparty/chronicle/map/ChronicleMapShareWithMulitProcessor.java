/**
 * 
 */
package me.power.speed.test.thirdparty.chronicle.map;

import java.io.File;
import java.util.Map;

import net.openhft.chronicle.map.ChronicleMapBuilder;

/**
 * @author xuehui.miao
 *
 */
public class ChronicleMapShareWithMulitProcessor {
	private static File file = null;
	private static Map<Integer, CharSequence> map = null;
	
	static {
		try {
			String path = System.getProperty("java.io.tmpdir") + "/mapshare";
			file = new File(path);
			map = ChronicleMapBuilder.of(Integer.class, CharSequence.class).createPersistedTo(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void put(Integer key, String value) {
		map.put(key, value);
	}
	
	public static String get(Integer key) {
		return (String)map.get(key);
	}
}
