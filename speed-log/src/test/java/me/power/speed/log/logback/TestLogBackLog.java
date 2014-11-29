/**
 * 
 */
package me.power.speed.log.logback;

import org.junit.Before;
import org.junit.Test;

import me.power.speed.log.AbstractTest;

/**
 * @author keke
 *
 */
public class TestLogBackLog extends AbstractTest {
	private LogBackLog log = null;
	
	@Before
	public void before() {
		try {
			LogBackConfigLoader.loadFromClassPath("logback-speed.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
		log = new LogBackLog();
	}
	
	@Test
	public void testLogInfo() {
		log.setLogOneDynamicParameter("logType", "speed");
		log.logInfo("this is info test");
	}
	
}
