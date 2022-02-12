package com.wangweijun.myapplication

import org.junit.Test

class UseClassTest {

    @Test
    fun 使用伴生对象() {
        // ka 对象 与 a 对象不是一个哦
        val ka = KotlinA()
        ka.putNumber(23232)
        KotlinA.a.putNumber(123)

        val empty = KotlinA.isEmpty("xxx")
        println("empty = $empty")
        println("KotlinA.HEIGHT = ${KotlinA.HEIGHT}")
        println("KotlinA.HEIGHT = $HEIGHT_OUT")
    }
}