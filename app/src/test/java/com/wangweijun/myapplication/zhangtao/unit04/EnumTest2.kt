package com.wangweijun.myapplication.zhangtao.unit04

import org.junit.Test

class EnumTest2 {

    @Test
    fun testOperation2() {
        val operation = Operation2.valueOf("ADD")
        when (operation) {
            Operation2.ADD -> println("add")
            Operation2.MINUS -> println("MINUS")
            Operation2.MULTI -> println("MULTI")
            Operation2.DIVI -> println("DIVI")
            else -> println("not all ")
        }
    }
    enum class Operation2(val value: String) {
        // 枚举 ADD 与 "ADD"必须一致
        ADD("ADD"),
        MINUS("MINUS"),
        MULTI("MULTI"),
        DIVI("DIVI")
    }

}