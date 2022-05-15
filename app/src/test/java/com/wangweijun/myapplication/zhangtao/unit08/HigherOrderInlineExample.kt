package com.wangweijun.myapplication.zhangtao.unit08


// HigherOrderInlineExample.kt
/*
多了一个关键字, copy inline 中的函数代码至调用处, inline一般修饰高阶函数
   ↓                                    */
inline fun fooInline(block: () -> Unit) {
    println("code in function fooInline")
    block()
}

fun main() {
    var i = 0
    fooInline{
        i++
    }
}