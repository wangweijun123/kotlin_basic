package com.wangweijun.myapplication;

import static java.lang.System.out;

public class A implements AInterface{
    public static final A a = new A();

    @Override
    public void putNumber(int num) {
        out.println("is int");
    }

    @Override
    public void putNumber(Integer num) {
        out.println("is Integer");
    }
}
