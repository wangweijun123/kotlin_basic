package com.wangweijun.myapplication.zhangtao.unit05.last

import org.junit.Test

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/10 4:12 下午
 * version: 1.0
 * desc   :
 */
class Test {

    @Test
    fun testPigManager() {
        // 被编译成了  PigManager.INSTANCE.login();
        PigManager.login()
    }

    @Test
    fun testPPPPPerson() {
        PPPPPerson.InnerSingleton.foo()
        PPPPPerson.InnerSingleton.foo2()
    }

    @Test
    fun testPPPPPerson2() {
        PPPPPerson2.foo()
        PPPPPerson2.foo2()
    }

    @Test
    fun testUUUser2() {
        val create = UUUser2.create("xx")
        println(create)
    }



}