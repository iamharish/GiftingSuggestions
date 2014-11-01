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
	
	/*@Pointcut("execution(* nvz.services..*.compute*(..))")
	public void logsForComputeMethods(){
	}
	
	@AfterReturning(pointcut = "logsForComputeMethods()", returning = "result")
	public void logReturnValuesForComputeMethods(double result){
		logger.info("Returned "+result);
	}
	
	@Pointcut("execution(* hars.services.OrderProcessor.newOrder(..))")
	public void logsForOrderObject(){
	}
	
	@Around("logsForOrderObject() && args(targetArgs)")
	public void logReturnValuesForComputeMethods(ProceedingJoinPoint joinPt, Object targetArgs) throws Throwable{
		Order order = (Order)targetArgs;
		logger.info("Order Total Before Processing "+order.getTotal());
		long startTime = System.currentTimeMillis();
		joinPt.proceed();
		long endTime = System.currentTimeMillis();
		logger.info("Order Total After Processing "+order.getTotal());
		logger.info("Total time for processing this order: "+(endTime-startTime)+" milli seconds");
	}
	
	@AfterReturning("execution(* hars.services.OrderProcessor.newOrder(..)) && args(targetArgs)")
	public void logForTaxValue(Order targetArgs){
		logger.info("Tax Value for Order: "+targetArgs.getId()+" is "+ targetArgs.getTax());
	}
	
	@AfterThrowing(pointcut="execution(* hars.services.OrderProcessor.newOrder(..))", throwing="ex")
	public void logForTaxValue(JoinPoint joinPt, Exception ex){
		logger.info("An excetion occured while processing the order which has been rolled back now. Message: "+ex.getMessage());
	}
	
	@Before("execution(* nvz.services.InventoryService*.adjust*(..))")
	public void logForInventoryBefore(JoinPoint joinPt){
		logger.info("Available Inventory Before Current Order: "+((InventoryService)joinPt.getTarget()).getInventory());
	}
	
	@After("execution(* nvz.services.InventoryService*.adjust*(..))")
	public void logForInventoryAfter(JoinPoint joinPt){
		logger.info("Available Inventory After Current Order: "+((InventoryService)joinPt.getTarget()).getInventory());
	}*/
}
