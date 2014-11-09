/**
 * 
 */
package me.power.speed.common.util;

import java.text.DecimalFormat;

/**
 * @author xuehui.miao
 *
 */
public class NumberUtil {
	//取一位整数  0
	//取一位整数和两位小数   0.00
	//取两位整数和三位小数，整数不足部分以0填补。 00.000
	//取所有整数部分 #
	//以百分比方式计数，并取两位小数  #.##%
	//显示为科学计数法，并取五位小数  #.#####E0
	//显示为两位整数的科学计数法，并取四位小数   00.####E0
	//将格式嵌入文本  光速大小为每秒,###米
	private static DecimalFormat decimalFormatThousand = new DecimalFormat(",###");
	
	public static String formatObject2thousand(Object obj) {
		if(obj == null) {
			return null;
		}
		return decimalFormatThousand.format(obj);
	}
}
