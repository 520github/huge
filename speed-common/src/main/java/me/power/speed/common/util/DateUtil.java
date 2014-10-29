/**
 * 
 */
package me.power.speed.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author xuehui.miao
 *
 */
public class DateUtil {
	public static String utc2Local(String utcTime, String utcTimePatten,
	   String localTimePatten) {
		  SimpleDateFormat utcFormater = new SimpleDateFormat(utcTimePatten);
		  utcFormater.setTimeZone(TimeZone.getTimeZone("UTC"));
		  Date gpsUTCDate = null;
		  try {
		   gpsUTCDate = utcFormater.parse(utcTime);
		  } catch (ParseException e) {
		   e.printStackTrace();
		  }
		  SimpleDateFormat localFormater = new SimpleDateFormat(localTimePatten);
		  localFormater.setTimeZone(TimeZone.getDefault());
		  String localTime = localFormater.format(gpsUTCDate.getTime());
		  return localTime;
	 }
	
	public static Date parseString2UTCDate(String sourceDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		sdf.setTimeZone(TimeZone.getDefault());
		//String re = sdf.format(new Date());
		return sdf.parse(sourceDate);
	}
}
