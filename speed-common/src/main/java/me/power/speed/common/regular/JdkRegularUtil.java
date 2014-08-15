/**
 * 
 */
package me.power.speed.common.regular;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xuehui.miao
 *
 */
public class JdkRegularUtil {
	
	
	public static List<String> getGroupListByPattern(String value, String pattern) {
		List<String> resultList = new ArrayList<String>();
		Pattern p = Pattern.compile(pattern); 
		Matcher m = p.matcher(value);
		while(m.find()) {
			String matchResult = m.group(0);
			resultList.add(matchResult);
		}
		
		return resultList;
	}
}
