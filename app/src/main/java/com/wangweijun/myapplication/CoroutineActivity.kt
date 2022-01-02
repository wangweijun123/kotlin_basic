package com.wangweijun.myapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class CoroutineActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_coroutine)
    }

    fun useJavaThreadForNetwork(view: View) {

    }
    fun useCoroutineForNetwork(view: View) {

    }
    fun clearText(view: View) {}
}