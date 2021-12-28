package com.wangweijun.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun javaCallKotlin(view: View) {
        Test.sayMessage("hi kotlin obj in kotlin")
        // 注意在kotlin中传java的class的写法 SecondActivity::class.java
        startActivity(Intent(applicationContext, SecondActivity::class.java))
    }
}