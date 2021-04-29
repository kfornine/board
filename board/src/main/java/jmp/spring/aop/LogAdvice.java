package jmp.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Component
@Log4j
public class LogAdvice {
	
	@Before("execution(* jmp.spring.service.BoardService.*(..))")
	public void logBefore() {
		log.info("AOP=====================");
	}
	
	@Around("execution(* jmp.spring.service.*.*(..))")
	public Object logTime(ProceedingJoinPoint pjp) {
		//@before
		//메서드 실행전 시간
		long startTime = System.currentTimeMillis();
		Object res = null;
		try {
			res = pjp.proceed();
		} catch (Throwable e) {
			//@AfterThrowing
			e.printStackTrace();
		}
		//메서드 실행후 시간
		long endTime = System.currentTimeMillis();
		//@after
		log.info("-0-0-0-0-0-0-0-0-0-0-0-" + (endTime - startTime));
		//타겟 객체의 실행 전, 후의 시간을 측정하여 실행시간을 로그에 기록합니다.
		return res;
	}

	
}
