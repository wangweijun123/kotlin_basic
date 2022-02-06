package com.wangweijun.myapplication.zhangtao.unit08


// HigherOrderInlineExample.kt
/*
多了一个关键字
   ↓                                    */
inline fun fooInline(block: () -> Unit) {
    block()
}

fun main() {
    var i = 0
    fooInline{
        i++
    }
}