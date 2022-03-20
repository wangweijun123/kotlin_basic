package com.wangweijun.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);
        // java调用kotlin代码,
        UtilsKt.echo("ddddddddddddd");

        Test.INSTANCE.sayMessage("hi kotlin obj in java");
        Test.jvmStaticTest("hi jvmStaticTest in java");
    }

    public void startTimer(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Observable.interval(1, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.i("wangweijun", "accept noraml");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i("wangweijun", "exception");
                    }
                });
            }
        }).start();

    }

}
