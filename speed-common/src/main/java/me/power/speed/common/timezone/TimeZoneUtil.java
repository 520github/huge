/**
 * 
 */
package me.power.speed.common.timezone;

import java.util.Date;
import java.util.TimeZone;

/**
 * @author xuehui.miao
 *
 */
public class TimeZoneUtil {
	
	/**
	 * 获取所有时区的列表数据
	 * @return
	 */
	public static String[] fetchAllTimeZoneIds() {
		return TimeZone.getAvailableIDs();
	}
	
	/**
	 * 获取系统当前默认时区与UTC的时间差.(单位:毫秒)
	 * @return
	 */
	public static int getDefaultTimeZoneRawOffset() {
		return TimeZone.getDefault().getRawOffset();
	}
	
	/**
	 * 获取指定时区与UTC的时间差.(单位:毫秒)  
	 * @param timeZoneId 时区Id 
	 * @return
	 */
	public static int getTimeZoneRawOffset(String timeZoneId) {
		return TimeZone.getTimeZone(timeZoneId).getRawOffset();
	}
	
	/**
	 * 获取系统当前默认时区与指定时区的时间差.(单位:毫秒) 
	 * @param timeZoneId 时区Id
	 * @return
	 */
	public static int getDiffTimeZoneRawOffse(String timeZoneId) {
		return TimeZone.getDefault().getRawOffset() -  TimeZone.getTimeZone(timeZoneId).getRawOffset();
	}
	
	/**
	 * 获取两个时区之间的时间差(单位:毫秒) 
	 * @param firstTimeZoneId   
	 * @param secordTimeZoneId
	 * @return
	 */
	public static int getDiffTimeZoneRawOffse(String firstTimeZoneId, String secordTimeZoneId) {
		return TimeZone.getTimeZone(firstTimeZoneId).getRawOffset() -  TimeZone.getTimeZone(secordTimeZoneId).getRawOffset();
	}
	
	/**
	 * 根据某个时区的时间，去获取另外一个时区的时间
	 * @param date
	 * @param firstTimeZoneId
	 * @param secordTimeZoneId
	 * @return
	 */
	public static Date getDateByTimeZone(Date date, String firstTimeZoneId, String secordTimeZoneId) {
		if(date == null) {
			return null;
		}
		int diffTimeZone = getDiffTimeZoneRawOffse(firstTimeZoneId, secordTimeZoneId);
		return new Date(date.getTime() - diffTimeZone);
	}
	
	/**
	 * 获取当前默认时区的id
	 * @return
	 */
	public static String getDefaultTimeZoneId() {
		return TimeZone.getDefault().getID();
	}
	
}
