package com.wangweijun.myapplication;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);

        UtilsKt.echo("ddddddddddddd");

        Test.INSTANCE.sayMessage("hi kotlin obj in java");
    }
}
