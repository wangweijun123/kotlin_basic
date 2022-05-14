package com.wangweijun.myapplication.zhangtao.unit04

import org.junit.Test

class EnumTest2 {

    @Test
    fun testOperation2() {
        val operation = Operation2.valueOf("ADD")
        println("operation.value = " + operation.value)
        when (operation) {
            Operation2.ADD -> println("add")
            Operation2.MINUS -> println("MINUS")
            Operation2.MULTI -> println("MULTI")
            Operation2.DIVI -> println("DIVI")
        }
    }
    enum class Operation2(val value: String) {
        // 枚举 ADD 与 "ADD"必须一致
        ADD("ADD"),
        MINUS("MINUS"),
        MULTI("MULTI"),
        DIVI("DIVI")
    }


    @Test
    fun testDuanXiaDirection() {
        val value = FaceVerifyState.FACE_VERIFY_FAILED.value
        println("value = $value")
        val valueOf = FaceVerifyState.valueOf(value)
        println("valueOf = $valueOf")
        println("valueOf == FaceVerifyState.FACE_VERIFY_FAILED ? ${valueOf == FaceVerifyState.FACE_VERIFY_FAILED}")
    }

    @Test
    fun testxx() {
        val flag = false
        val re = if (flag) 1 else 0
        println("re = $re")
    }
    enum class FaceVerifyState(val value: String) {
        FACE_VERIFY_SUCCESS("FACE_VERIFY_SUCCESS"),
        FACE_VERIFY_USER_CANCEL("FACE_VERIFY_USER_CANCEL"),
        FACE_VERIFY_FAILED("FACE_VERIFY_FAILED")
    }
}