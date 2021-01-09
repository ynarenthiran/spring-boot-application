package spring.course.configuration;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@Aspect
public class AspectConfig {

	@Pointcut("execution(* spring.course.service.*.*(..))")
	public void serviceMerticsPointcut() {
	}

	@Before("serviceMerticsPointcut()")
	public void serviceMerticsStart(JoinPoint joinPoint) {
		System.out.println("Start of service-" + joinPoint.getSignature().toString() + new Date());
	}

	@After("serviceMerticsPointcut()")
	public void serviceMerticsEnd(JoinPoint joinPoint) {
		System.out.println("End of Service-"  + joinPoint.getSignature().toString() + ":"+ new Date());
	}

	@Pointcut("execution(* spring.course.controller.*.*(..))")
	public void controllerPointcut() {
	}

	@Around("controllerPointcut()")
	public Object controllerMertics(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("Start of controller-" + new Date());
		Object proceed = joinPoint.proceed();
		System.out.println("End of controller-" + new Date());
		return proceed;
	}

	@AfterThrowing(pointcut = "execution(* spring.course.controller.*.getAllEmployee(..))", throwing = "exception")
	public void logExceptions(JoinPoint joinPoint, Exception exception) {
		System.out.println("logExceptions-"+ joinPoint.getSignature().toString() + ":" + exception);
	}

	@AfterReturning(pointcut = "execution(* spring.course.controller.*.getAllEmployee(..))", returning = "val")
	public void logReturnValues(JoinPoint joinPoint, Object val) {
		System.out.println("logReturnValues:" + joinPoint.getSignature().toString() + "::" + val);
	}

	@Before("@annotation(MerticLogger)")
	public void merticLogger(JoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		System.out.println("MerticLogger of service-" + joinPoint.getSignature().toString() + ":::" + start);
	}

}
