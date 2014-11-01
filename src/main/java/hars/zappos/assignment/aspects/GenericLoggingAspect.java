package hars.zappos.assignment.aspects;


import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class GenericLoggingAspect {
	private Logger logger = Logger.getLogger(GenericLoggingAspect.class);

	@Pointcut("execution(* hars..*.*(..))")
	public void allMethods() {
	}

	@Before("execution(* hars..*.*(..))")
	public void logBeforeAllMethods(JoinPoint joinPt) {
		logger.info("Method started: " + joinPt.getSignature());
		Object[] methodArgs = joinPt.getArgs();
		int argCount = 1;
		for(Object arg: methodArgs){
			logger.info("Method argument "+argCount+" "+arg);
			argCount++;
		}
	}
	
	@After("execution(* hars..*.*(..))")
	public void logAfterAllMethods(JoinPoint joinPt) {
		logger.info("Method finished: " + joinPt.getSignature());
	}
	
	@AfterThrowing(pointcut="execution(* hars..*.*(..))", throwing="ex")
	public void logForTaxValue(JoinPoint joinPt, Exception ex){
		logger.info("An excetion occured has occured. Message: "+ex.getMessage());
	}	
}