package com.wangweijun.myapplication.zhangtao.unit06

import org.junit.Test

open class Baby {
    var name: String = ""
    var age: Int = 0
}

class Helper {
    val Baby.isAdult: Boolean
        get() = age >= 18

    fun Baby.walk() {
        walkOnFoot()
    }

    private fun walkOnFoot() {
        println("用脚走路")
    }
    @Test
    fun test() {
        val baby = Baby()
        // 仅可以在Helper类当中使用此扩展
        baby.walk()
    }
}