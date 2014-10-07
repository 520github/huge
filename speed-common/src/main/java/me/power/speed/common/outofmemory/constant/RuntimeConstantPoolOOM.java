/**
 * 
 */
package me.power.speed.common.outofmemory.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuehui.miao
 *
 */
public class RuntimeConstantPoolOOM {
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		int i =0;
		while(true) {
			list.add(String.valueOf(i++).intern());
		}
	}
}
