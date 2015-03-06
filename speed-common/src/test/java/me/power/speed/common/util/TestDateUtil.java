/**
 * 
 */
package me.power.speed.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import me.power.speed.AbstractBaseTest;

import org.junit.Test;

/**
 * @author xuehui.miao
 *
 */
public class TestDateUtil extends AbstractBaseTest {
	
	/*
在系统中，如果没有明确指明的地方。日期的中间传输采用RFC3339格式：
yyyy-MM-dd’T’HH:mm:ss’Z’
例如：
1996-12-19T16:39:57+08:00
或
1996-12-19T08:39:57Z
由于Java对时区偏移的格式化标准是RFC822，所以，我们采用第二个例子。这样对于一个Date对象的格式化示例代码如下：
import java.util.*;
import java.text.*;
…
SimpleDateFormat sdf = new SimpleDateFormat(“yyyy-MM-dd’T’HH:mm:ss’Z’”);
sdf.setTimeZone(TimeZone.getTimeZone(“UTC”));
sdf.format(new Date());
	 */
	
	@Test
	public void testUtc2Local() {
		String utcTime = "2014-10-28T03:34:40+0700";
		utcTime = "2014-10-29T04:07:57Z";
		String utcTimePatten = "yyyy-MM-dd'T'HH:mm:ssZ";
		utcTimePatten = "yyyy-MM-dd'T'HH:mm:ss'Z'";
		String localTimePatten = "yyyy-MM-dd'T'HH:mm:ss'Z'";
		localTimePatten = "yyyy-MM-dd HH:mm:ss";
		localTimePatten = "yyyyMMdd";
		localTimePatten = "yyMMdd";
		// = "yyyy-mm-dd HH:mm:ss";
		String result = DateUtil.utc2Local(utcTime, utcTimePatten, localTimePatten);
		this.print(result);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		String re = sdf.format(new Date());
		this.print(re);
	}
	
	
	
	@Test
	public void testParseString2UTCDate() {
		try {
			String sourceDate = "2014-10-28T03:34:40Z";
			this.print(sourceDate);
			Date result = DateUtil.parseString2UTCDate(sourceDate);
			this.print(result.toString());
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	@Test
	public void testDiffDay() {
		long day_mills = 1000*60*60*24;
		int day_mills_int = 1000*60*60*24;;
		long ct = System.currentTimeMillis();
		long bt = System.currentTimeMillis() - (day_mills*170);
		
		long diff = ct - bt;
		System.out.println("diff-->" + diff);
		//System.out.println("" + );
		int day = (int)(diff/day_mills);
		System.out.println("day-->" + day);
		if(diff/day_mills >=180) {
			System.out.println("is more than 180 by " + diff/day_mills);
		}
		else {
			System.out.println("is less than 180 by " + diff/day_mills);
		}
		
		System.out.println("longValue:" + (day_mills*200));
		System.out.println("intValue:" + (day_mills_int*200));
	}
}
