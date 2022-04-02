package com.example.android

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wangweijun.myapplication.R

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/03 12:05 上午
 * version: 1.0
 * desc   :
 */
class GizmosActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val action = intent?.action
        val data: Uri? = intent?.data

        setContentView(R.layout.activity_deep_link)
    }
}