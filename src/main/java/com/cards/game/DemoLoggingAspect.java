package com.cards.game;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DemoLoggingAspect {

    @Before("execution(public org.springframework.http.ResponseEntity save(com.cards.game.models.Card))")
    public void beforeSave(JoinPoint joinPoint) {
        System.out.println("[DemoLoggingAspect] - Saving new card...");

        /** Get method signature */
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println(methodSignature);

        /** Get object */
        Object[] args = joinPoint.getArgs();
        for (Object a: args) {
            System.out.println(a.toString());
        }
    }

    @AfterReturning( pointcut = "execution(public * com.cards.game.services.*.save(*))",
            returning = "result")
    public void afterSaving(JoinPoint joinPoint, Object result) {
        System.out.println("Saving finished. Saved: " + result.toString());
    }
}
