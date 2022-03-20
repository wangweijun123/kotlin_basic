package com.wangweijun.myapplication.zhangtao.unit3

import org.junit.Test

class NullOrEmptyTest {

    @Test
    fun testEnum() {
        var str: String? = null
        isNullOrBlank2(str)
    }

    fun isNullOrBlank2(str: String?) {
        if (!str.isNullOrBlank()) {
            println("#######")
        } else {
            println("isNullOrBlank")
        }
    }
    @Test
    fun testForIn() {


        for (i in 1..4) { // 'downTo' 使用替换
            println("$i ")
        }
        println("测试小的 ")
        for (i in 1..1) { // 'downTo' 使用替换
            println("$i ")
        }
    }


}