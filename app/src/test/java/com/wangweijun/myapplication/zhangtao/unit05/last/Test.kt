package com.wangweijun.myapplication.zhangtao.unit05.last

import org.junit.Assert
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

//    @Test
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

    @Test
    fun testPigUtils() {
        // 调用端看起来调用object的 实例方法与 jvmstatic方法看起来一致，其实
        // 其实是不一样的，实例方法，反编译后还是会先获取实例，再调用实例方法

    // 调用的是单实例方法 PigUtils.INSTANCE.getAge();
        println(PigUtils.age)
    // 这个反编译不变化 PigUtils.getPigs();
        println(PigUtils.getPigs())
    // 这个反编译调用单实例的方法 : PigUtils.INSTANCE.noJvmStatic();
        println(PigUtils.noJvmStatic())
    }

    @Test
    fun testLastUserManager() {
        val instanceKotlin1 = LastUserManager.getInstanceKotlin2("xxx")
        val instanceKotlin2 = LastUserManager.getInstanceKotlin2("xxx")
        Assert.assertEquals(instanceKotlin1, instanceKotlin2)
    }

    @Test
    fun testDatabaseManager() {
        val myinstance1 = DatabaseManager.myinstance
        val myinstanc2 = DatabaseManager.myinstance
        Assert.assertEquals(myinstance1, myinstanc2)
    }

}