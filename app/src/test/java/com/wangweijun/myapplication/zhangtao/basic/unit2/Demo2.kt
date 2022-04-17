package com.wangweijun.myapplication.zhangtao.basic.unit2

import com.wangweijun.myapplication.zhangtao.basic.unit1.BasiTypeDemo
import org.junit.Test

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/10 12:11 上午
 * version: 1.0
 * desc   :
 */
class Demo2 {
    @Test
    fun test() {
        val basiTypeDemo: BasiTypeDemo = BasiTypeDemo()
        basiTypeDemo.test01()

        val num: Double = 10.0
        val num2 = Double.MAX_VALUE
    }

    @Test
    fun testCopy() {
        val personData = PersonData("dx", 18)
        val copy = personData.copy(name = "wxj")
        println("copy = $copy")
        val copy1 = personData.copy("wxj")
        println("copy1 = $copy1")
    }
}