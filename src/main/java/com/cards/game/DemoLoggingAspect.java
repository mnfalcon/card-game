package com.cards.game;

import com.cards.game.handler.GlobalExceptionHandler;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {

    private static Logger log = Logger.getLogger(GlobalExceptionHandler.class.getName());

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
        log.info("Saving finished. Saved: " + result.toString());
    }
}
