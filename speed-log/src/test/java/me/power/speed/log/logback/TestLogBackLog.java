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
			//LogBackConfigLoader.loadFromClassPath("logback.xml");
			//LogBackConfigLoader.load("logback-speed.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
		log = new LogBackLog();
	}
	
	@Test
	public void testLogInfo() {
		log.setLogOneDynamicParameter("logType", "job");
		log.logInfo("this is like test");
	}
	
}
