package com.wangweijun.myapplication.unit1

import org.junit.Test
import java.io.File

/**
 * 扩展函数(静态的对某个class进行方法与变量的添加)
 */
class MethodExtends {
    @Test
    fun testFileExtends() {
        val file = File("src/1.txt")
        val content = file.readText()
        println(content)
    }
}