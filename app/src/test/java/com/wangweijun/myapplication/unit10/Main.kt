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
            get() = 100
    }

    class MyInterfaceImp2 : MyInterface {
        override val age: Int
            get() = 100
    }
    // 返回的是单例
    private fun getMyInterface() = MyInterfaceImp

    private fun getMyInterface2(): MyInterface {
        return MyInterfaceImp2()
    }

    @Test
    fun testEnumClass() {
        val myInterface1 = getMyInterface()
        val myInterface11 = getMyInterface()
        Assert.assertEquals(myInterface1, myInterface11)
    }
}