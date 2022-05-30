package com.wangweijun.myapplication.zhangtao.unit12

import org.junit.Test

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/03/20 6:18 下午
 * version: 1.0
 * desc   :
 */

class JavaConvertExample5 {
    //      稍后初始化
    private lateinit var name: String // 不可能为空的 String 类型
    fun init() {
        name = "tom"
    }

    @Test
    fun test() {
//        init()
        if (this::name.isInitialized) {
            val count = name.length
            println("count=$count")
        } else {
            println("Please call init() first!")
        }
    }

}