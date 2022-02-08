package com.wangweijun.myapplication.zhangtao.unit09

import org.junit.Test

class ByLazyTest {

    //            定义懒加载委托
//               ↓   ↓
    val data: String by lazy {
        request()
    }

    fun request(): String {
        println("执行网络请求")
        return "网络数据"
    }

    /*结果：
    开始
    执行网络请求
    网络数据
    网络数据*/
    @Test
    fun main2() {
        println("开始")
        println(data)
        println(data)
    }
}