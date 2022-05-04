package com.wangweijun.myapplication.zhangtao.basic.unit1

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/10 12:33 上午
 * version: 1.0
 * desc   : 函数是一等公民,直接写在kotlin文件中
 *   1 ， 文件会编译成一个类 public final class FunctionInFileKt
 *   2, 函数编译成public static
 */

// public static final
//
fun myAdd(a: Int, b: Int) = a + b

val addFunction = ::myAdd

