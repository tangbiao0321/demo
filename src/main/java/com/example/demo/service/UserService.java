package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.anno.RecallMethod;
import com.example.demo.entity.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @RecallMethod(name = "创建用户请求")
    public void createUser(Users users, int count, List<Users> userNos) {
        System.out.println("createUser:"+JSONObject.toJSONString(users));
System.out.println("list:"+JSONObject.toJSONString(userNos));
        throw new RuntimeException("异常测试");
    }
    @RecallMethod(name = "创建用户请求2")
    public void createUser(Users users) {
        System.out.println("createUser:"+JSONObject.toJSONString(users));
        throw new RuntimeException("异常测试");
    }

    @RecallMethod(name = "创建用户请求3")
    public void createUser() {
        throw new RuntimeException("异常测试");
    }
}
