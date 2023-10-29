package com.wangweijun.myapplication.zhangtao.last

import org.junit.Test

class BasicTest {

    @Test
    fun testClass() {
        val xx = XXXBean("zhangtao", 15)
        xx.height = 200
        println(xx.isAdult) // false
        println(xx.isAdult2) // false

        xx.age = 30
        println(xx.isAdult) // true
        println(xx.isAdult2) // false 注意这里是false，因为isAdult2是一个属性，只会在初始化的时候计算一次
    }
}