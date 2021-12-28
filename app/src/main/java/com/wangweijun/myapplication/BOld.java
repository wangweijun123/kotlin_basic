package com.wangweijun.myapplication;

import static java.lang.System.out;

public class BOld implements AInterface{
    public static final BOld a = new BOld();

    @Override
    public void putNumber(int num) {
        out.println("is int");
    }

    @Override
    public void putNumber(Integer num) {
        out.println("is Integer");
    }
}
