package com.wangweijun.myapplication.user

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.wangweijun.myapplication.R

class MyActivity  : AppCompatActivity() {
//    val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_coroutine)
//        contnet = findViewById<TextView>(R.id.mContent)

    }
}