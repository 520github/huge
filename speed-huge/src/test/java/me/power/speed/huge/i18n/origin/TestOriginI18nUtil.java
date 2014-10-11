/**
 * 
 */
package me.power.speed.huge.i18n.origin;

import java.util.Locale;

import org.junit.Test;

/**
 * @author xuehui.miao
 *
 */
public class TestOriginI18nUtil {
	
	@Test
	public void testOriginI18n() {
		String result = OriginI18nUtil.getResourceBundle("i18n/resource", Locale.CHINA).getString("message.user.username");
		System.out.println(result);
	}
}
