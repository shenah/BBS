package com.pk.springboard.util;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAdvice {
	@Around("execution(public * com.pk.springboard..*Dao.*(..))")
	public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
		String methodName = joinPoint.getSignature().toLongString();
		System.out.println(methodName);
		long time = System.currentTimeMillis();
		Date date = new Date(time);
		java.sql.Date filename = new java.sql.Date(time);
		FileOutputStream file = 
				new FileOutputStream("/Users/a503-03/Documents/logs/" + filename.toString() + ".log", true);
		
		PrintWriter pw = new PrintWriter(file);
		pw.println(methodName + "-" + date.toString() + "\n" );
		pw.flush();
		pw.close();
		Object obj = joinPoint.proceed();

		return obj;
	}
}

