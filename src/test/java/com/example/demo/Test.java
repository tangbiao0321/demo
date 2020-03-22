package com.example.demo;

import org.assertj.core.util.Lists;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        String testStr = "_0sdf_ABC_EVENT_A";
        List<String> strList = Lists.list("ABC","BCF");
        long count = strList.stream().filter(e->{
                return testStr.matches("(\\w)_[A-Z]");
        }).count();
        System.out.println("match count="+count);


        String v = testStr.replaceAll("(\\w_EVENT)_[A-Z]$", "$1");
        System.out.println("replaceAll v="+v);
        System.out.println(strList.contains(v));
    }
}
