package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.FreezOptDTO;
import org.springframework.stereotype.Service;

@Service("accountRemainHandler")
public class CreditCntHandler implements FreezRuleHandler {


    @Override
    public boolean execute(FreezOptDTO freezOptDTO) {
        System.out.println("accountRemainHandler"+JSONObject.toJSONString(freezOptDTO));
        return false;
    }

    @Override
    public String execute1(FreezOptDTO freezOptDTO) {
        System.out.println(JSONObject.toJSONString(freezOptDTO));
        return String.join("", "10", freezOptDTO.getOperator(), freezOptDTO.getRuleVal());
    }
}
