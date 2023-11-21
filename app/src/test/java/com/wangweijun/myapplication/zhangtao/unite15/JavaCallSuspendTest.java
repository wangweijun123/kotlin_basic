package com.wangweijun.myapplication.zhangtao.unite15;

import androidx.annotation.NonNull;

import org.junit.Test;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;

public class JavaCallSuspendTest {

    @Test
    public void testxxx() throws InterruptedException {
        SuspendTestKt.getUserInfo(new Continuation<String>() {
            @NonNull
            @Override
            public CoroutineContext getContext() {
                System.out.println("getContext:");
                return EmptyCoroutineContext.INSTANCE;
            }

            @Override
            public void resumeWith(@NonNull Object o) {
                System.out.println("resumeWith:"+o);
            }
        });

        Thread.sleep(2000);
    }
}
