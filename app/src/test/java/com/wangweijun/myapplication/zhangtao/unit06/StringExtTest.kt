package com.wangweijun.myapplication.zhangtao.unit06

import org.junit.Test

class StringExtTest {
    @Test
    fun testStringExt() {
        val msg = "Hello Wolrd"
        // lastElement就像String的成员方法一样可以直接调用
        val last = msg.lastElement() // last = d
        println("last : $last")
    }

    @Test
    fun testStringExt2() {
        val msg = "Hello Wolrd"
        msg.trim()
        // lastElement就像String的成员方法一样可以直接调用
        val last = msg.lastElement
        println("last : $last")
    }

    @Test
    fun test() {
        val baby = Baby()
        // 仅可以在Helper类当中使用此扩展
//        baby.walk() // build error
    }
}