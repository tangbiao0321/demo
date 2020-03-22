package com.example.demo.aspect;

import org.springframework.stereotype.Component;

@Component
public class Config {

    public static ThreadLocal<Boolean> recall = ThreadLocal.withInitial(()->Boolean.FALSE);
    public static ThreadLocal<Integer> recall12 = new ThreadLocal<>();
    public ThreadLocal<Integer> recall22 = new ThreadLocal<>();

    public void setRecall22(int recall22) {
        this.recall22.set(recall22);
    }

    public Integer getRecall22() {
        return recall22.get();
    }
}
