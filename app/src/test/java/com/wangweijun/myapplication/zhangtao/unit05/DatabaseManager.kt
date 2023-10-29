package com.wangweijun.myapplication.zhangtao.unit05

class DatabaseManager private constructor(){
    companion object {
        val instance by lazy { DatabaseManager() }
    }
}