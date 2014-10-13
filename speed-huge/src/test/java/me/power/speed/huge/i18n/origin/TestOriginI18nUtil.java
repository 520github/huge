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
//http://blog.csdn.net/hanqunfeng/article/details/4879912
//http://blog.csdn.net/wangpeng047/article/details/8994414
public class TestOriginI18nUtil {
	
	@Test
	public void testOriginI18n() {
		String result = OriginI18nUtil.getResourceBundle("i18n/resource", Locale.CHINA).getString("message.user.username");
		System.out.println(result);
	}
}
