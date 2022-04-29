package com.wangweijun.myapplication.zhangtao.basic.unit2

import com.wangweijun.myapplication.zhangtao.MySealedClass
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

    @Test
    fun testSealClass() {
        val mySealedClass: MySealedClass = MySealedClass.EQUAL
        findSealedClass(mySealedClass)
        when (mySealedClass) { // seal class 没有检查是否齐全？？？？？
            MySealedClass.EQUAL -> println("xxx")
        }
        println("MySealedClass.EQUAL === MySealedClass.EQUAL ? ${MySealedClass.EQUAL === MySealedClass.EQUAL}")
    }

    fun findSealedClass(mySealedClass: MySealedClass) {
        when (mySealedClass) {
            is MySealedClass.EQUAL -> println("equla")
        }
    }

    @Test
    fun testMyResult() {
        val myResult = MyResult.Success("success")
        val myResult2 = MyResult.Success("success")
        //myResult === myResult2 ? false, 就是说不同的对象
        println("myResult === myResult2 ? ${myResult === myResult2}")
        displayMyUi(myResult)
    }

    private fun displayMyUi(myResult: MyResult) {
        when (myResult) {
            is MyResult.Success -> println("success")
            is MyResult.Error -> println("error")
        }
    }

    @Test
    fun testCodeFlow() {
        val str: String? = null
        val str2: String? = "duanxia"
        println("str?.length = ${str?.length}") // str?.length = null 后续的代码会执行的哦
        println("str2?.length = ${str2?.length}") // str2?.length = 7

       val len = str2?.length ?: 0
        println("len = $len")
    }
}