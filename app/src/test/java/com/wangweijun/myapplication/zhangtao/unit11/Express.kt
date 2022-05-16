package com.wangweijun.myapplication.zhangtao.unit11

import org.junit.Test

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/02/20 1:32 上午
 * version: 1.0
 * desc   :
 */

class Express {

    @Test
    fun mainTest() {
        val data = null
        javaExpress(data)
        kotlinExpress(data)

        val data2 = 100
        javaExpress(data2)
        kotlinExpress(data2)
    }

    @Test
    fun whenString() {
        val currentFaceSource = null
        val transType = when (currentFaceSource) {
            "KYC" -> "user"
            "WITHDRAW" -> "withdrawFace"
            else -> ""
        }
        println("transType = $transType")
    }

    fun kotlinExpress(data: Int?) {
        println("####kotlinExpress#### data = $data")
        // ?: 相当于 if (data!=null) i=data else i=0
        // ?: 相当于if else，前面是不为空的情况，后面是为空的情况
        var i = data ?: 0
        println(" i = $i")
        var j = data ?: getDefault()
        println(" j = $j")
        /*var k = data ?: throw NullPointerException()
        println("k = $k")*/
        // if else 有返回值
        var x = when (data) {
            is Int -> data
            else -> 0
        }
        println("x = $x")

        // try catch 也有返回值
        var y =  try {
            "99".toInt()
        } catch (e: NumberFormatException) {
            println(e)
            0
        }
        println("y = $y")

    }

    fun javaExpress(data: Int?) {
        println("####javaExpress#### data = $data")
        var i = 0
        if (data != null) {
            i = data
        }
        println(" i = $i")
        var j = 0
        if (data != null) {
            j = data
        } else {
            j = getDefault()
        }
        println("j = $j")

        /*var k = 0
        if (data != null) {
            k = data
        } else {
            throw NullPointerException()
        }
        println("k = $k")*/

        var x = 0
        when (data) {
            is Int -> x = data
            else -> x = 0
        }
        println("x = $x")

        var y = 0
        try {
            y = "99".toInt()
        } catch (e: NumberFormatException) {
            println(e)
            y = 0
        }
        println("y = $y")
    }

    private fun getDefault() = 1

    @Test
    fun testException() {
        val data: Int? = 100
        val result = try {
            exceptionExcpress(data)
        } catch (e: java.lang.NumberFormatException) {
            println("catch number format exception")
            -10
        }
        println("result = $result")
    }

    fun exceptionExcpress(data: Int?): Int {
        val i = data ?: throw java.lang.NumberFormatException("number is null")
        println("i = $i")
        return i
    }

    // throw NotImplementedError() 是一个表达式

    fun calculate(): Int = throw NotImplementedError()


    //       函数返回值类型是Int，实际上却抛出了异常，没有返回Int
//                ↓       ↓
    fun calculate2(): Int = throw NotImplementedError()
}