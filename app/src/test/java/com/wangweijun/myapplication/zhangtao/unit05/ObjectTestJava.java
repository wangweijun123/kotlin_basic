package com.wangweijun.myapplication.zhangtao.unit05;

import static java.lang.System.out;

import org.junit.Test;

public class ObjectTestJava {

    @Test
    public void testSingleton() {
        UserManager.INSTANCE.login();
    }

    @Test
    public void testSingleton2() {
        PPPerson.InnerSingleTon.INSTANCE.foo();
        PPPerson.InnerSingleTon2.foo2();
    }
}
