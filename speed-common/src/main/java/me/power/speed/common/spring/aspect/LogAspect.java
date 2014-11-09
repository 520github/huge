/**
 * 
 */
package me.power.speed.common.spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

/**
 * @author xuehui.miao
 *
 */
public class LogAspect {
	@Around("execution(public * com.talkingdata.push.rest..*.*(..))")
	public Object logRestMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		return log(joinPoint);
	}

	@Around("execution(public * com.talkingdata.push.dao..*.*(..))")
	public Object logDaoMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		return log(joinPoint);
	}

	@Around("execution(public * com.talkingdata.push.service..*.*(..))")
	public Object logServiceMethod(ProceedingJoinPoint joinPoint)
			throws Throwable {
		return log(joinPoint);
	}
	
	private Object log(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		
		Object retVal = joinPoint.proceed();
		
		long end = System.currentTimeMillis();
		long differ = end - start;
		
		StringBuffer logMessage = new StringBuffer();
		logMessage.append("execute(ms):").append(differ)
				.append("|").append(joinPoint.getTarget().getClass().getName()).append(".").append(joinPoint.getSignature().getName())
				.append("|");
		Object[] args = joinPoint.getArgs();
		for (int i = 0; i < args.length; i++) {
			logMessage.append(args[i]).append(",");
		}
		if (args.length > 0) {
			logMessage.deleteCharAt(logMessage.length() - 1);
		}

		logMessage.append("|");
		logMessage.append(retVal);
		
		//LogPortal.getTimeConsumeLog().logDebug(logMessage.toString());
		
		return retVal;
	}
}
