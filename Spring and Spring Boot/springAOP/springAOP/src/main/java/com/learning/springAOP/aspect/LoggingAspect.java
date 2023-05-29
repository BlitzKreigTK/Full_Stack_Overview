package com.learning.springAOP.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

// Define the class as configuration
//Class will be using AOP
@Configuration
@Aspect
public class LoggingAspect {
	private Logger logger = LoggerFactory.getLogger(getClass());

	// When to execute --> Point cut (@Before/@Pointcut)
	// execution(* PACKAGE.*.*(..)): In the PACKAGE intercept each subclass methods
	@Before("com.learning.springAOP.aspect.CommonPointcutAspect.serviceAndRepositoryPackage()")
	public void beforeLogMethodCall(JoinPoint joinPoint) {
		// What to execute --> Logger Method
		// JoinPoint can be used to get details of method intercepted
		logger.info("Logging details before method called -> {}", joinPoint);
	}

	@After("com.learning.springAOP.aspect.CommonPointcutAspect.repositoryPackage()")
	public void afterLogMethodCall(JoinPoint joinPoint) {
		logger.info("Logging details after method {} called args --> {}", joinPoint, joinPoint.getArgs());
	}

	@AfterThrowing(pointcut = "com.learning.springAOP.aspect.CommonPointcutAspect.servicePackage()", throwing = "exception")
	public void afterSuccessLogMethodCall(JoinPoint joinPoint, Exception exception) {
		// throwing = "exception" will bind exception thrown to "exception"
		logger.info("Logging details after method {} throwing exception {}", joinPoint, exception);
	}

	@AfterReturning(pointcut = "com.learning.springAOP.aspect.CommonPointcutAspect.servicePackage()", returning = "result")
	public void afterExceptionLogMethodCall(JoinPoint joinPoint, Object result) {
		// returning = "result" will bind result from method to "Object result"
		logger.info("Logging details after method {} successfully executed {}", joinPoint, result);
	}
}
