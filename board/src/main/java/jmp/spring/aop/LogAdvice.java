package jmp.spring.aop;

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
}
