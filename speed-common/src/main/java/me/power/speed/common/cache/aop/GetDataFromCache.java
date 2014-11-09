/**
 * 
 */
package me.power.speed.common.cache.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xuehui.miao
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface GetDataFromCache {
	String value();
	
	int timeScope() default 600;
	
	String condition() default "";
}
