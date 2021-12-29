package com.wangweijun.myapplication.unit2;

import static java.lang.System.out;

import org.junit.Test;

public class LambdaDemo {
    @Test
    public  void lambdaTest() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                out.println("xxxx");
            }
        });
        thread.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public  void lambdaTest2() {
        Thread thread = new Thread(()->{
            out.println("xxxx");
        });
        thread.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
