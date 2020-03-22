package com.example.demo.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum FreezRuleEnums {
    BIG_CREDIT_CNT("freezeTypeRules", "bigCreditCnt","bigCreditCntHandler","","&&", "用户邀请大额授信人数（30天内）"),
    ACCOUNT_REMAIN("freezeTypeRules", "accountRemain","accountRemainHandler","","&&", "当前账户余额"),
    FREEZE_LOAN_AMOUNT("freezeTypeRules", "loanAmountRate","loanAmountRateHandler","","||", "被邀请人动支总金额/大额授信人数"),
    FREEZE_LOAN_PERSON("freezeTypeRules", "loanPersonRate","loanPersonRateHandler","%","||", "被邀请人动支人数/大额授信人数"),
    FREEZE_PRE_REPAY("freezeTypeRules", "preRepayRate","preRepayRateHandler","%","||", "被邀请人提前还款人数/邀请人数"),

    UN_FREEZE_LOAN_AMOUNT("unFreezeTypeRules","loanAmountRate","loanAmountRateHandler","","||", "被邀请人动支总金额/大额授信人数"),
    UN_FREEZE_LOAN_PERSON("unFreezeTypeRules","loanPersonRate","loanPersonRateHandler","%","||", "被邀请人动支人数/大额授信人数"),
    UN_FREEZE_PRE_REPAY("unFreezeTypeRules","preRepayRate","preRepayRateHandler","%","||", "被邀请人提前还款人数/邀请人数");

    /** 规则类型 **/
    private String ruleType;

    /** 规则名称 **/
    private String ruleName;

    /** 规则code **/
    private String ruleCode;

    /** 规则处理 **/
    private String process;

    /** 规则值符号 **/
    private String postfix;

    /** 表达式连接符 **/
    private String join;


    FreezRuleEnums(String ruleType, String ruleCode, String process, String postfix, String join, String ruleName) {
        this.ruleType = ruleType;
        this.ruleCode = ruleCode;
        this.ruleName = ruleName;
        this.process = process;
        this.postfix = postfix;
        this.join = join;
    }

    public static FreezRuleEnums getEnums(String ruleType, String ruleCode) {
        return Arrays.asList(FreezRuleEnums.values()).stream()
                .filter(e->e.getRuleType().equals(ruleType))
                .filter(e->e.getRuleCode().equals(ruleCode))
                .findFirst().orElse(null);
    }

    public static List<FreezRuleEnums> getFreezList() {
        return Arrays.asList(FreezRuleEnums.values()).stream()
                .filter(e->e.getRuleType().equals("freezeTypeRules"))
                .collect(Collectors.toList());
    }
    public static List<FreezRuleEnums> getUnFreezList() {
        return Arrays.asList(FreezRuleEnums.values()).stream()
                .filter(e->e.getRuleType().equals("unFreezeTypeRules"))
                .collect(Collectors.toList());
    }

    public String getRuleType() {
        return ruleType;
    }

    public String getRuleName() {
        return ruleName;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public String getProcess() {
        return process;
    }

    public String getPostfix() {
        return postfix;
    }

    public String getJoin() {
        return join;
    }
}
