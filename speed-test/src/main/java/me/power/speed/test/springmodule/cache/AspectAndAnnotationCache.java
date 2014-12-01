/**
 * 
 */
package me.power.speed.test.springmodule.cache;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author xuehui.miao
 *
 */
@Aspect
public class AspectAndAnnotationCache {
	//simple cache
	private Map<String,Object> cacheMap = new HashMap<String, Object>();
	
	@Around("execution(public * com.power.speed.test..*.*(..))")
	public Object getAndCache(ProceedingJoinPoint joinPoint) throws Throwable {
		CacheAnnotation ca = this.getAnnotation(joinPoint, CacheAnnotation.class);
		//not any cache annotation
		if(ca == null) {
			System.out.println("annotion is null");
			return joinPoint.proceed();
		}
		String key = this.getMethodCacheKey(joinPoint);
		if(cacheMap.containsKey(key)) {
			System.out.println("get " + key + " value from " );
			return cacheMap.get(key);
		}
		
		Object result = joinPoint.proceed();
		cacheMap.put(key, result);
		
		return result;
	}
	
	private <T extends Annotation> T getAnnotation(ProceedingJoinPoint joinPoint, Class<T> clazz) {
		MethodSignature joinPointObject = (MethodSignature) joinPoint.getSignature();
		Method method = joinPointObject.getMethod();
		return method.getAnnotation(clazz);
	}
	
	private String getMethodCacheKey(ProceedingJoinPoint joinPoint) {
		StringBuffer key = new StringBuffer();
		key.append(joinPoint.getTarget().getClass().getName());
		key.append(".");
		key.append(joinPoint.getSignature().getName());
		Object[] objects = joinPoint.getArgs();
		for(Object object : objects) {
			key.append(object).append(",");
		}
		
		if(key.toString().endsWith(",")) {
			key.deleteCharAt(key.length()-1);
		}
		
		return key.toString();
	}
}
