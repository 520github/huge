/**
 * 
 */
package me.power.speed.http.servlet;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * @author xuehui.miao
 *
 */
public class ServletRequestUtil {
	
	private static String unknownIp = "";
	
	public static String getRealIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if(StringUtils.isBlank(ip) || unknownIp.equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(StringUtils.isBlank(ip) || unknownIp.equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(StringUtils.isBlank(ip) || unknownIp.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
