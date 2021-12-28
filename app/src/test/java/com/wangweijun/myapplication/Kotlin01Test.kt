package com.wangweijun.myapplication

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class Kotlin01Test {
    @Test
    fun addition_isCorrect() {
        val age = 5;
        println("hi, kotlin $age") // 可以不加 ${}
    }

    @Test
    fun main() {
        val age = 5; // val 不能再赋值
//        age = 23;
        var name = 22; // 没问题
        name = 33;
        var height = 100.4 // 虽然你没指定数据类型,但是编译器推断出来了,所以下面哪行出错了
        // height = 50 // error
        val weight: Int = 200 // 类型自动推断
        var myL: String = "duanxia" // 类型自动推断
//        Char 占两个字节,不是基本类型
        var cc = 'c'
        cc = 3.toChar();
        println("hi, ${myL} main kotlin weight = ${weight}")
    }

    @Test
    fun main2() {
        // 两种写法
        // 第一种写法
        // 循环执行23次, {}中每次执行代码
        repeat(23, {
            print('x')
            print(it) // it内置的变量,当前的索引
        })
        println()
        // 第二种写法
        repeat(5) {
            print('x')
            print(it) // it内置的变量
        }
    }

    /**
     * =======================
    Happy Birthday, Jhansi!
    =======================
     */
    @Test
    fun printBorder() {
        //kotlin 中的 Any 就是java中的Object
        val signal = "#"
        val times = 10
        printSignal(signal, times)
        println()
        val name = "duanxia"
        val content = "Happy Birthday, ${name}!"
        println(content)
        printSignal(signal, times)
    }

    fun printSignal(sig: String, times: Int) { // 格式化 ctrl + shirt + l
        // 我想让你double一下
        repeat(times * 2) {
            print(sig)
        }
    }
}