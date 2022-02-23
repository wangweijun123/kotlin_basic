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

    @Test
    fun testDefaultParams() {
        val towFa = TowFa(scen = "login", name = "wxk")
        println(towFa)
    }
}

data class TowFa(
    val scen: String,
    val age: Int = 100, // 默认参数
    val name: String
)