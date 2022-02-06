package com.wangweijun.myapplication.zhangtao.unit08

fun foo(block: () -> Unit) {
    block()
}

fun main() {
    var i = 0
    foo{
        i++
    }
}