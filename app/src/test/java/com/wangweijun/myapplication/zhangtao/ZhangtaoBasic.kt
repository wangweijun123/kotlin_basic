package com.wangweijun.myapplication.zhangtao

import com.wangweijun.myapplication.age
import com.wangweijun.myapplication.name
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
        // kotlin原始格式字符串,使用 """
        val str2 = """ ad\nbc """
        println(str2)
    }

    @Test
    fun arrayTest() {
        val a = arrayOf("4", "0", "7", "i", "f", "w", "0", "9")
//        a.size
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
    // 单一表达式函数，单一就是函数体只有一行,类型推倒
    fun helloFunction2(name: String) = "Hello $name !"

    fun helloFunction3(name: String = "duanxia", smallName: String = "xiao duanxia") = "$name, $smallName !"
    fun helloFunction3(name: String = "duanxia", smallName: String = "xiao duanxia", age: Int) = "$name, $smallName,$age !"

    @Test
    fun invokeFunctionByParamName() {
        println(helloFunction2("xxx"))
        println(helloFunction2(name = "duanxia"))
        println(helloFunction3(name = "wxj", smallName = "yaya"))
        println(helloFunction3(smallName = "baby"))
        println(helloFunction3(age = 11))
        // 传入形参的名字, 那肯定是给这个形参附值
        println(helloFunction3(smallName = "yaya", name = "wxj"))
    }

    @Test
    fun addition_isCorrect() {
        val age = 5;
        // 能调用方法，所以2就是一个对象,Int对象
        // kotlin 没有原始类型
        println(2.toDouble())
    }

    @Test
    fun returnIf() {
        val i = -1
        //
        val msg = if (i > 0) "big" else "Small"
        // if else居然还可以是不同类型
        val msg2 = if (i > 0) "big" else 1
        println(msg)
        println(msg2)
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
        // elvis 表达式 ?. 表示text不为null的情况，?: 表示else
        return text?.length ?: 0
    }

    data class MyObject(var num: Int)

    @Test
    fun elvisTestObj() {
        var myObj: MyObject? = MyObject(1)
        myObj = null
        val result = myObj?.num ?: -10
        println("result = $result")
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
        }
    }
    // 表达式，我的理解就是有返回值
    @Test
    fun whenTest2() {
        val i: Int = 1
        // when 中的情况必须完整，覆盖到所有情况,与java中switch case不一样罗，大部分有else，除非枚举完毕
        val message = when(i) {
            1 -> "一"
            2 -> "二"
            else -> "i 不是一也不是二" // 如果去掉这行，会报错
        }
        print(message)
    }

    @Test
    fun forTest() {
        val arrayOf: Array<Int> = arrayOf(1, 2, 3)
        val arrayOf2: IntRange = 1..3 //
        val arrayOf3: IntRange = 3..1 // 编译不会错，只是不循环
        for (i in arrayOf) {
            println(i)
        }
        println("######")
        for (i in arrayOf2) {
            println(i)
        }
        println("######")
        for (i in 6 downTo 0 step 2) { println(i)}

        println("###arrayOf3###")
        for (i in arrayOf3) {
            println(i)
        }

    }

    @Test
    fun testRunCatch() {
    }

    @Test
    fun fp() {
        listOf(1, 2, 3, 4)
            .filter {
                it % 2 == 0
            }
            .forEach {
                println(it)
            }
    }


    fun testSum() {
        val sum = (1..10).sum() // 结果 55
    }
}