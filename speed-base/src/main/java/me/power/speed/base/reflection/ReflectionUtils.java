/**
 * 
 */
package me.power.speed.base.reflection;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author keke
 *
 */
public class ReflectionUtils {
	public static Method findMethod(Class clazz, String name, Class[] paramTypes) {
		Class searchType = clazz;
		while (!Object.class.equals(searchType) && searchType != null) {
			Method[] methods = (searchType.isInterface() ? searchType.getMethods() : searchType.getDeclaredMethods());
			for (int i = 0; i < methods.length; i++) {
				Method method = methods[i];
				if (name.equals(method.getName()) &&
						(paramTypes == null || Arrays.equals(paramTypes, method.getParameterTypes()))) {
					return method;
				}
			}
			searchType = searchType.getSuperclass();
		}
		return null;
	}
}
