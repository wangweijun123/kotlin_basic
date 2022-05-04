package com.wangweijun.myapplication.zhangtao.unit05.last

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/05/03 10:15
 * version: 1.0
 * desc   : 通过 by lazy 语法实现单利，最简单
 */
class DatabaseManager private constructor(){

    companion object {
        @JvmStatic
        val myinstance by lazy { DatabaseManager() }
    }
}