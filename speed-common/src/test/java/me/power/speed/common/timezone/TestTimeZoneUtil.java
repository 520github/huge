/**
 * 
 */
package me.power.speed.common.timezone;

import java.util.Date;

import org.junit.Test;

import me.power.speed.AbstractBaseTest;

/**
 * @author xuehui.miao
 *
 */
public class TestTimeZoneUtil extends AbstractBaseTest {
	
	@Test
	public void testFetchAllTimeZoneIds() {
		String result[] = TimeZoneUtil.fetchAllTimeZoneIds();
		this.print(result);
	}
	
	@Test
	public void testGetDefaultTimeZoneRawOffset() {
		int result = TimeZoneUtil.getDefaultTimeZoneRawOffset();
		this.print(result);
		this.print(result/1000/60/60);
	}
	
	@Test
	public void testGetTimeZoneRawOffset() {
		int result = TimeZoneUtil.getTimeZoneRawOffset("");
		this.print(result);
		this.change2hour(result);
	}
	
	@Test
	public void testAllTimeZoneRawOffset() {
		String results[] = TimeZoneUtil.fetchAllTimeZoneIds();
		for(String timeZoneId : results) {
			int result = TimeZoneUtil.getTimeZoneRawOffset(timeZoneId);
			this.print(timeZoneId + ":" +result);
			this.print(timeZoneId + ":" +result/1000/60/60);
		}
	}
	
	@Test
	public void testGetDiffTimeZoneRawOffse() {
		int result = TimeZoneUtil.getDiffTimeZoneRawOffse("Pacific/Kiritimati");
		this.change2hour(result);
	}
	
	@Test
	public void testGetDateByTimeZone() {
		String firstTimeZoneId = TimeZoneUtil.getDefaultTimeZoneId();
		String secordTimeZoneId = TimeZoneUtil.getDefaultTimeZoneId();
		Date date = this.getCurrentDate();
		this.print(date.toString());
		
		secordTimeZoneId = "Indian/Cocos";//6
		this.getDateByTimeZone(date, firstTimeZoneId, secordTimeZoneId);
		
		secordTimeZoneId = "Pacific/Kiritimati";//14
		this.getDateByTimeZone(date, firstTimeZoneId, secordTimeZoneId);
		
		secordTimeZoneId = "Atlantic/Faroe";//0
		this.getDateByTimeZone(date, firstTimeZoneId, secordTimeZoneId);
		
		secordTimeZoneId = "Atlantic/Stanley";//-3
		this.getDateByTimeZone(date, firstTimeZoneId, secordTimeZoneId);
		
		secordTimeZoneId = "SystemV/EST5";//-5
		this.getDateByTimeZone(date, firstTimeZoneId, secordTimeZoneId);
		
		secordTimeZoneId = "America/Shiprock";//-7
		this.getDateByTimeZone(date, firstTimeZoneId, secordTimeZoneId);
		
		secordTimeZoneId = "Mexico/BajaNorte";//-8
		this.getDateByTimeZone(date, firstTimeZoneId, secordTimeZoneId);
		
		firstTimeZoneId = "Africa/Juba";//3
		secordTimeZoneId = "Indian/Cocos";//6
		this.getDateByTimeZone(date, firstTimeZoneId, secordTimeZoneId);
	}
	
	private void getDateByTimeZone(Date date, String firstTimeZoneId, String secordTimeZoneId) {
		Date result = TimeZoneUtil.getDateByTimeZone(date, firstTimeZoneId, secordTimeZoneId);
		this.print(result.toString());
	}
	
	@Test
	public void testGetDefaultTimeZoneId() {
		String defaultTimeZoneId = TimeZoneUtil.getDefaultTimeZoneId();
		this.print(defaultTimeZoneId);
	}
	
	private void change2hour(int value) {
		this.print(value/1000/60/60);
	}
}
