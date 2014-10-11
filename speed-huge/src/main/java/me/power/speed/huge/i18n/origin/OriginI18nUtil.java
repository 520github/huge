/**
 * 
 */
package me.power.speed.huge.i18n.origin;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author xuehui.miao
 *
 */
public class OriginI18nUtil {
	
	public static Locale getDefaultLocale() {
		return Locale.getDefault();
	}
	
	public static ResourceBundle getResourceBundle(String sourceName, Locale locale) {
		return ResourceBundle.getBundle(sourceName, locale);
	}
	
}
