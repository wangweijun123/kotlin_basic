package com.wangweijun.myapplication.zhangtao.unit06.last

import org.junit.Test

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/05/04 10:30
 * version: 1.0
 * desc   :
 */
class Demo {

    @Test
    fun testMyPig() {
        MyPig.test1()
//        MyPig.test2() // build error
    }

    @Test
    fun testMyDDog() {
        val dog = MyDDog()
        dog.test1()
        dog.test2()
    }

    @Test
    fun testDuanxiaPerson() {
        val person = DuanxiaPerson()
//        person.walk() // build error
    }

    @Test
    fun testMyStringExt() {
        val str: String = "xx"
        str.lastElement2()
        str.lastElement3()

        val str2: String? = null
//        str2.lastElement2() // build error,因为不为空的接收者类型
        str2.lastElement3()
    }

}