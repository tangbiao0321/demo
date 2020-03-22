package com.example.demo.service;

import com.example.demo.entity.FreezOptDTO;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorString;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
@Log4j2
public class FreezFunction extends AbstractFunction implements InitializingBean {

    private static final String FUN_NAME = "freeze";

    @Resource
    private ApplicationContext applicationContext;

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3) {
        try{
            Object param = FunctionUtils.getJavaObject(arg1, env);
            String compare=FunctionUtils.getStringValue(arg2, env);
            String target = FunctionUtils.getStringValue(arg3, env);
            FreezOptDTO freezOptDTO=(FreezOptDTO)param;
            freezOptDTO.setOperator(compare);
            freezOptDTO.setRuleVal(target);
            FreezRuleEnums freezRuleEnums = FreezRuleEnums.getEnums(freezOptDTO.getRuleType(), freezOptDTO.getRuleCode());
            FreezRuleHandler handler = (FreezRuleHandler) applicationContext.getBean(freezRuleEnums.getProcess());
            boolean flag=handler.execute(freezOptDTO);
            return flag?AviatorBoolean.TRUE:AviatorBoolean.FALSE;
        }catch(Exception e){
            log.error("执行比较发生异常,",e);
        }
        return AviatorBoolean.FALSE;
    }

    @Override
    public String getName() {
        return FUN_NAME;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        AviatorEvaluator.addFunction(this);
    }
}
