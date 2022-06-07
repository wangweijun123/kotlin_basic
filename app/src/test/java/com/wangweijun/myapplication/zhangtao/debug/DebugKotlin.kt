package com.wangweijun.myapplication.zhangtao.debug

import org.junit.Test

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/06/07 12:13
 * version: 1.0
 * desc   :
 */
class DebugKotlin {

    @Test
    fun testDebug() {
        var flag = true
        if (flag) { // 这里修改变量值还是跑上面的分支，和编译器相关，去掉了if语句
            println("if flag = $flag")
        } else {
            println("else flag = $flag")
        }
    }

    @Test
    fun testDebug2() {
        var flag = false
        checkPP(flag)
    }

    fun checkPP(flag: Boolean) {
        if (flag) {
            println("if flag = $flag")
        } else {
            println("else flag = $flag")
        }
    }


    @Test
    fun testDebug3() {
        val add = add()
        println("add=$add")
    }

    fun add(): Boolean {
        val add = false
        println("addd")
        return add
    }
}