package com.learning.springAOP.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcutAspect {

	// For all service/repository package classes common point cut
	@Pointcut("execution(* com.learning.springAOP.*.*.*(..))")
	public void serviceAndRepositoryPackage() {
	}

	// For all service package classes common point cut
	@Pointcut("execution(* com.learning.springAOP.service.*.*(..))")
	public void servicePackage() {
	}

	// For all repository package classes common point cut
	@Pointcut("execution(* com.learning.springAOP.repository.*.*(..))")
	public void repositoryPackage() {
	}

	// Common point cut for @Service beans
	@Pointcut("bean(*Service*)")
	public void allServiceAndRepositoryPackage() {
	}

	// Common point cut for @TrackTime custom annotation
	@Pointcut("@annotation(com.learning.springAOP.annotations.TrackTime)")
	public void customAnnotation() {
	}
}
