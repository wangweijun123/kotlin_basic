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

    fun kotlinExpress(data: Int?) {
        println("####kotlinExpress#### data = $data")
        var i = data ?: 0
        println(" i = $i")
        var j = data ?: getDefault()
        println(" j = $j")
        /*var k = data ?: throw NullPointerException()
        println("k = $k")*/

        var x = when (data) {
            is Int -> data
            else -> 0
        }
        println("x = $x")


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


}