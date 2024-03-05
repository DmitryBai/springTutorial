package spring.tutorial.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import spring.tutorial.aop.Account;

import java.util.Arrays;
import java.util.List;

@Aspect
@Order(1)
@Component
public class MyLoggingAspect {


    @Around("execution(* spring.tutorial.aop.service.TrafficFortuneService.getFortune(..)))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("\n======>>> Executing @Around on method " + proceedingJoinPoint.getSignature().toShortString());

        long begin = System.currentTimeMillis();

        Object result = null;

        try {
             result = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            System.out.println(e.getMessage());

            result = "Accident! No worries!";

            //throw e;
        }

        long end = System.currentTimeMillis();

        long duration = end - begin;

        System.out.println("\n======>>> Duration is " + duration);

        return result;
    }

    @After("execution(* spring.tutorial.aop.dao.AccountDAO.findAccounts(..)))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {
        System.out.println("\n======>>> Executing @After on method " + joinPoint.getSignature().toShortString());
    }

    @AfterThrowing(
            pointcut = "execution(* spring.tutorial.aop.dao.AccountDAO.findAccounts(..)))",
            throwing = "e"
    )
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable e) {
        System.out.println("\n======>>> Executing @AfterThrowing on method " + joinPoint.getSignature().toShortString());

        System.out.println("\n======>>> The exception is " + e);
    }

    @AfterReturning(
            pointcut = "execution(* spring.tutorial.aop.dao.AccountDAO.findAccounts(..)))",
            returning = "result"
    )
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {
        System.out.println("\n======>>> Executing @AfterReturning on method " + joinPoint.getSignature().toShortString());

        System.out.println("\n======>>> Result is: " + result);

        // post-process data
        postProcessResult(result);
    }

    private void postProcessResult(List<Account> result) {
        result.get(0).setName("Post-processed name");
    }

    //@Before("execution(public void addAccount())")
    //@Before("execution(public void spring.tutorial.aop.dao.AccountDAO.addAccount())")
    //@Before("execution(public void spring.tutorial.aop.dao.AccountDAOImpl.addAccount())")
    //@Before("execution(public void add*())")
    //@Before("execution(* add*())")
    //@Before("execution(* add*(spring.tutorial.aop.Account))")
    //@Before("execution(* add*(spring.tutorial.aop.Account, ..))")
    //@Before("execution(* add*(..))")
    //@Before("forDaoPackage()")
    @Before("spring.tutorial.aop.aspect.AopPointcutExpressions.forDaoPackageNoSettersNoGetters()")
    public void logAccount(JoinPoint joinPoint) {
        System.out.println("Method signature: "+ joinPoint.getSignature());
        for (Object arg : joinPoint.getArgs()) {
            System.out.println(arg);
        }
        System.out.println("\n=====> LOGGING");
    }

}
