package com.wangweijun.myapplication.unit8

const val a = 0
const val str = "wangweijun"

fun constTest() {
//    a = 2; build error
}

object AA {
    // Const 'val' are only allowed on top level or in objects
   const val num = 10
}