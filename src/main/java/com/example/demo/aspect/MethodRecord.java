package com.example.demo.aspect;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.SignatureSpi;

@Aspect
@Component
public class MethodRecord {
    @Resource
    ApplicationContext applicationContext;

    @AfterThrowing(value = "@annotation(com.example.demo.anno.RecallMethod)", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex){
        System.out.println("AfterThrowing...");
        if (Config.recall.get()){
            System.out.println("job recall, 不记录异常信息");
            return;
        }

        System.out.println("目标方法名为:" + joinPoint.getSignature().getName());
        System.out.println("目标方法所属类的简单类名:" +        joinPoint.getSignature().getDeclaringType().getSimpleName());
        System.out.println("目标方法所属类的类名:" + joinPoint.getSignature().getDeclaringTypeName());
        System.out.println("目标方法声明类型:" + Modifier.toString(joinPoint.getSignature().getModifiers()));
        //获取传入目标方法的参数
        Object[] args = joinPoint.getArgs();
        System.out.println(JSONObject.toJSONString(args));
        for (int i = 0; i < args.length; i++) {
                System.out.println("第" + (i+1) + "个参数为:" + JSONObject.toJSONString(args[i]));
                System.out.println("第" + (i+1) + "个参数为:" + args[i].getClass().getName());
            }
        System.out.println("被代理的对象:" + JSONObject.toJSONString(joinPoint.getTarget().getClass()));
        System.out.println("代理对象自己:" + JSONObject.toJSONString(joinPoint.getThis().getClass()));

//        System.out.println(JSONObject.toJSONString(joinPoint));
        System.out.println(ex.getMessage());


        String[] beanNames = applicationContext.getBeanNamesForType(joinPoint.getTarget().getClass());
        System.out.println(JSONObject.toJSONString(beanNames));
    }
}
