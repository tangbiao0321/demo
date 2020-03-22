package com.example.demo.service;

import com.example.demo.entity.FreezOptDTO;

public interface FreezRuleHandler {

    boolean execute(FreezOptDTO freezOptDTO);

    String execute1(FreezOptDTO freezOptDTO);
}
