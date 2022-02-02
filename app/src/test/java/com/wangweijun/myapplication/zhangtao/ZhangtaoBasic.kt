package com.wangweijun.myapplication.zhangtao

import com.wangweijun.myapplication.age
import org.junit.Test

class ZhangtaoBasic {

    @Test
    fun basicType() {
        val int = 1
        val long = 1234567L
        val double = 13.14
        val double2 = 13.14
        val float = 13.14F
        val hexadecimal = 0xAF
        val binary = 0b01010101
        val binary2 = 0b00000101
        println(binary2)

        val c: Char = 'A'
        val i: Int = c.toInt()
    }

    @Test
    fun setValueForNullType() {
        var i: Double = 1.0
//        var j: Double? = null
        var j: Double? = 2.0
//        i = j build error
        if (j != null) {
            i = j
            println("a i = $i")
        }
        j?.let {
            i = it
            println("b i = $i")
        }
    }

    @Test
    fun basicStringType() {
        // 换行了
        val str = "ad\nbc"
        println(str)
        // 原始格式字符串
        val str2 = """ ad\nbc """
        println(str2)
    }

    @Test
    fun arrayTest() {
        val a = arrayOf("4", "0", "7", "i", "f", "w", "0", "9")
        val index = arrayOf(5, 3, 9, 4, 8, 3, 1, 9, 2, 1, 7)
        index.filter {
            it < a.size
        }.map {
            a[it]
        }.reduce { p1, p2 ->
            "$p1$p2"
        }.also {
            println("密码是 $it")
        }
    }

    @Test
    fun arrayTest2() {
        val index = arrayOf(5, 3, 9, 4, 8, 3, 1, 9, 2, 1, 7)
        val list1 = index.filter {
            it < 6
        }
        println(index)
        println(list1)
    }

    @Test
    fun callMethod() {
//        createUser("dx", 23, 5)
//        createUser("dx", 23)
        // 调用方法时,把形参名字带上就不需要按照函数定义的顺序来调用了
        createUser("dx", gender = 55, age = 23)
    }


    private fun createUser(
        name: String,
        age: Int,
        gender: Int = 1
    ) {
        println("name=$name,age=$age,gender=$gender")
    }

    /*
    关键字    函数名          参数类型   返回值类型
     ↓        ↓                ↓       ↓      */
    fun helloFunction(name: String): String {
        return "Hello $name !"
    }/*   ↑
   花括号内为：函数体
*/
    // 单一表达式函数，单一就是函数体只有一行
    fun helloFunction2(name: String) = "Hello $name !"

    @Test
    fun addition_isCorrect() {
        val age = 5;
        // 能调用方法，所以2就是一个对象,Int对象
        // kotlin 没有原始类型
        println(2.toDouble())
    }

    @Test
    fun returnIf() {
        val i = 1
        //
        val msg = if (i > 0) "big" else "small"
        println(msg)
    }

    @Test
    fun returnIf2() {
        val i = 1
        //
        val msg = if (i > 0) {
            println("这里还有很多big快的代码，最后一行作为返回值")
            "big"
        } else {
            println("这里还有很多small快的代码，最后一行作为返回值")
            "small"
        }
        println(msg)
    }

    @Test
    fun elvisTest() {
        println(getLength2("abc"))
        println(getLength2(null))
    }

    fun getLength(text: String?): Int {
        return if (text != null) text.length else 0
    }

    fun getLength2(text: String?): Int {
        // elvis 表达式
        return text?.length ?: 0
    }

    @Test
    fun elvisTest2() {
        println(getString2(null))
        println(getString2("abc"))
        println(getString2("abcdefg"))
    }
    fun getString(text: String?): String {
        return if ((text?.length ?: 0) > 5) "大于5" else "小于5"
    }

    fun getString2(text: String?): String {
        return if (text?.length ?: 0 > 5) "大于5" else "小于5"
    }

    @Test
    fun whenTest() {
        val i: Int = 1
        when(i) {
            1 -> print("一")
            2 -> print("二")
            else -> print("i 不是一也不是二")
        }
    }

    @Test
    fun whenTest2() {
        val i: Int = 1
        val message = when(i) {
            1 -> "一"
            2 -> "二"
            else -> "i 不是一也不是二" // 如果去掉这行，会报错
        }
        print(message)
    }

    @Test
    fun testRunCatch() {
    }
}