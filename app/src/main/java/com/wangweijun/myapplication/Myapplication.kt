package com.wangweijun.myapplication

import android.app.Application
import com.wangweijun.myapplication.userLogin.AppContainer

class Myapplication : Application() {
    val appContainer = AppContainer()
    override fun onCreate() {
        super.onCreate()
    }
}