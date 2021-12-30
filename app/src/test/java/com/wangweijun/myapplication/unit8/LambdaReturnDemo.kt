package com.wangweijun.myapplication.unit8

/**
 * 1 在kotlin中 lambda表达式是无法中断外部函数执行的
 * 2 使用inline: 是可以中断外部调用函数的,原理代码的copy
 * 3
 */
fun lambdaReturn() {
    test1 {
        println("test1 闭包内打印")
//        return // build error
//        return@test1 // build success 但是无用
    }
    println("test1 闭包外打印")
}

fun lambdaReturnWithInline() {
    test2 {
        println("test2 inline 闭包内打印")
        return // build success: 因为被调用的函数加了 inline,结果中断外部函数执行了哈
//        return@test2 // build success
    }
    println("test2 inline 闭包外打印")
}

fun test1(l: () -> Unit) {
    l()
//    return 无用
}


/**
 * 注意这里 inline: 代码的copy, 所以在闭包内如果出现return,是可以中断
 * 外部的调用函数的, 直接返回, 也可以只返回闭包
 */
inline fun test2(l: () -> Unit) {
    l()
//    return 无用
}