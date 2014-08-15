/**
 * 
 */
package me.power.speed.common.regular;

import java.util.List;

import me.power.speed.AbstractBaseTest;

import org.junit.Before;
import org.junit.Test;

/**
 * @author xuehui.miao
 *
 */
public class TestJdkRegularUtil extends AbstractBaseTest {
	private String value = null;
	private String pattern = null;
	
	@Before
	public void before() {
		this.value = "981504:2014-08-14 18:10:40,270 [ INFO] [Push.java:108] logRequestHeader: [x-up-bear-type]:[TD-SCDMA];  [Host]:[gaandroid.talkingdata.net];  [X-Forwarded-For]:[10.242.107.244, 117.136.19.80];  [Content-Encoding]:[UTF-8];  [Content-Length]:[872];  [Content-Type]:[application/json];  [x-up-calling-line-id]:[8613913497322];  [Connection]:[close];  [X-Real-IP]:[117.136.19.80];";
		this.pattern = "\\[[-/\\w\\.\\,]+\\]:\\[[-/\\w\\.\\,]+\\];";
	}
	
	@Test
	public void testGetGroupListByPattern() {
		List<String> groupList = JdkRegularUtil.getGroupListByPattern(value, pattern);
		this.print(groupList);
	}
}
