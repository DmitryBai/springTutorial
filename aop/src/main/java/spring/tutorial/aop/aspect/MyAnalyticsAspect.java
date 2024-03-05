package spring.tutorial.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(2)
@Component
public class MyAnalyticsAspect {

    @Before("execution(* add*(..))")
    public void logAccount() {
        System.out.println("\n=====> ANALYTICS");
    }
}
