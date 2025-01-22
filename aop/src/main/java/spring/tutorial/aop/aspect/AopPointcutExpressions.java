package spring.tutorial.aop.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class AopPointcutExpressions {

    @Pointcut("execution(* spring.tutorial.aop.dao.*.*(..))")
    private void forDaoPackage(){}

    @Pointcut("execution(* spring.tutorial.aop.dao.*.get*())")
    private void getter() {}

    @Pointcut("execution(* spring.tutorial.aop.dao.*.set*())")
    private void setter() {}

    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoSettersNoGetters() {}

}
