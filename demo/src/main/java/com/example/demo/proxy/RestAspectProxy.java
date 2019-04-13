package com.example.demo.proxy;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.ProxyMethodInvocation;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * Created by Enzo Cotter on 2019/4/12.
 */
@EnableAspectJAutoProxy
@Aspect
@Component
public class RestAspectProxy {

	@Pointcut("execution(* org.springframework.web.client.RestTemplate.exchange(..))")
	public void restCallPoint() {
	}

	@Around("restCallPoint()")
	public ResponseEntity<? extends Object> restCallProxied(ProceedingJoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		MethodInvocationProceedingJoinPoint mipj = ((MethodInvocationProceedingJoinPoint) joinPoint);
		Class<?> returnType = fetchProxiedMethodReturnType(mipj);
		return new ResponseEntity<>("returnType", null, HttpStatus.OK);

	}

	public static Class<?> fetchProxiedMethodReturnType(MethodInvocationProceedingJoinPoint mipj) {
		Class<? extends MethodInvocationProceedingJoinPoint> mipjClass = mipj.getClass();
		try {
			Field field = mipjClass.getDeclaredField("methodInvocation");
			field.setAccessible(true);
			ProxyMethodInvocation methodInvocation = (ProxyMethodInvocation) field.get(mipj);
			Class<?> returnType = methodInvocation.getMethod().getReturnType();
			return returnType;
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

}
