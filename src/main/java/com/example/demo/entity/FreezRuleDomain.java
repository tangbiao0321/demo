package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class FreezRuleDomain implements Serializable {

    private String cashType;

    /** 类型:冻结、解冻 **/
    private String ruleType;

    /** 规则名称 **/
    private String ruleName;

    /** 规则code **/
    private String ruleCode;

    /** 规则运算符 **/
    private String operator;

    /** 规则值**/
    private String ruleVal;

    /** 规则值符号,% （显示使用，从FreezRuleTypeEnums枚举取）**/
    private String postfix;

    /** 操作人 **/
    private String updatedBy;
}
