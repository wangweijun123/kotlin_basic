package com.wangweijun.myapplication.unit10

import com.wangweijun.myapplication.zhangtao.ZT02
import org.junit.Assert
import org.junit.Test

class Main {

    interface MyInterface {
        val age: Int
    }
    //
    object MyInterfaceImp : MyInterface {
        override val age: Int
            get() = 10
    }

    class MyInterfaceImp2 : MyInterface {
        override val age: Int
            get() = 100
    }
    // 直接返回类名,返回的是单例
    private fun getMyInterface() = MyInterfaceImp

    private fun getMyInterface2(): MyInterface {
        return MyInterfaceImp2()
    }

    @Test
    fun testEnumClass() {
        val myInterface1 = getMyInterface()
        val myInterface11 = getMyInterface()
        // myInterface1 === myInterface11 ? true
        println("myInterface1 === myInterface11 ? ${myInterface1 === myInterface11}")
//        Assert.assertEquals(myInterface1, myInterface11)
        // myInterface2 === myInterface22 ? false
        val myInterface2 = getMyInterface2()
        val myInterface22 = getMyInterface2()
        println("myInterface2 === myInterface22 ? ${myInterface2 === myInterface22}")
    }
}