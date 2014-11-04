package hars.zappos.assignment.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Configurable;

@Aspect
public class SpecificLoggingAspect {
	private Logger logger = Logger.getLogger(SpecificLoggingAspect.class);
	
	@After("execution(* hars..*.addProducts(..)) && target(target)")
	public void logReturnValuesForComputeMethods(JoinPoint joinPt, Object target){
		logger.info("Suggested Result "+target);
	}
	
	@AfterReturning(pointcut = "execution(* hars..*(..))", returning = "result")
	public void logReturnValuesForMethods(Object result){
		logger.info("Returned "+result);
	}
}
