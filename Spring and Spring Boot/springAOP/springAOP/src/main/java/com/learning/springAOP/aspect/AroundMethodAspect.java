package com.learning.springAOP.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AroundMethodAspect {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Around("com.learning.springAOP.aspect.CommonPointcutAspect.customAnnotation()")
	public Object executionTime(ProceedingJoinPoint proceedJoinPoint) throws Throwable {
		// Start timer at execution of method starts
		long startTimeMillis = System.currentTimeMillis();
		// Method called
		Object returnValue = proceedJoinPoint.proceed();
		// Stop timer at execution of method stops
		long endTimeMillis = System.currentTimeMillis();
		// Total time = endTime - startTime;
		long executionTime = endTimeMillis - startTimeMillis;
		logger.info("Method {} executed in ms : {} ", proceedJoinPoint, executionTime);
		return returnValue;
	}
}