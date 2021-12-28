package com.wangweijun.myapplication

import org.junit.Test

/**
 * 在kotlin语言,变量与函数直接定义在文件中
 */

// val 定义的是一个不可变的变量, 但是不是常量
val age: Int = 18

// 这种是不可以为NULL的类型
var name: String = "wangweijun"
// 这种是可以为NULL的类型, 这是两种不同的类型
var name2: String? = null

@Test
fun main() {
//    printLen(name)
    // 把可以为NULL类型强制传入不可为NULL类型的函数,除非你确保可以为NULL类型的参数的值一定不为NULL,否则强制传入
    // 在运行期抛异常NullPointException
//    name2 = "xxx"
//    printLen(name2!!)
    callOtherKotlin()
}

/**
 * 直接调用其他Kotlin文件的函数
 */
fun callOtherKotlin() {
    echo("dxx")
}

/**
 * 在字符串中打印变量需要 $
 */
fun printVarInStr() {
    print("my age = $age")
}

/**
 * 赋值操作: 不为NULL类型可以为 可以为NULL类型赋值
 */
fun assign() {
    //    name = name2 // error 类型不匹配,但是可以强转,如下
//    name = name2!!
    name2 = name; // is ok
    print(name2)
}

/**
 * 参数 为 不为NULL 类型, 所以在函数内部就不需要去做判断
 */
fun printLen(str: String): String {
//    println("字符串为：$str")
    return str
}