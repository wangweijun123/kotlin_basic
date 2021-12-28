package com.wangweijun.myapplication;

import static java.lang.System.out;

import org.junit.Test;

public class Main {

    /**
     * java 调用 Kotlin 文件
     */
    @Test
    public  void javaCallKotlin() {
        UtilsKt.echo("wangweij");
        String myname = UtilsKt.getMyname();
        out.println("print in java  " + myname);
    }

    public void instanceMethod(String msg) {
        out.println(msg);
    }

    public static void staticMethod(String msg) {
        out.println(msg);
    }
}
