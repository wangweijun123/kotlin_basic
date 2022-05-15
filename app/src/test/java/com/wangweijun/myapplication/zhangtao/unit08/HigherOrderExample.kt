package com.wangweijun.myapplication.zhangtao.unit08

import org.junit.Assert
import org.junit.Test

class HighOrderTest {
    fun foo(block: () -> Unit) {
        println("code in function fooInline")
        block()
    }

//    @Test
    fun main() {
        var i = 0
        foo{
            i++
//            Assert.assertEquals(1, i)
        }
    }
}
