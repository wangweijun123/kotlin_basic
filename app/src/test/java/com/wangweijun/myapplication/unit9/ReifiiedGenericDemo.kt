package com.wangweijun.myapplication.unit9

import com.google.gson.Gson
// app\build\tmp\kotlin-classes\debugUnitTest\com\wangweijun\myapplication\unit9\ReifiiedGenericDemoKt.class
// 扩展函数
// asm byte view 与 javap -c 返汇编差不多结果
// 必须加 inline 与 reified(具体的)
// 被编译成如下 (synthetic 合成的)
// public final static synthetic fromJsonMe(Lcom/google/gson/Gson;Ljava/lang/String;)V

// 给com.google.gson.Gson 扩展函数, 那这个很强大了, 扩展函数
// 方式 Gson.methodName()
inline fun <reified T> Gson.fromJsonMe(json: String) {
    // 为什么能调用Gson中的fromJson, 因为现在就是写
    // 在Gson的扩展函数Gson.fromJsonMe
    fromJson(json, T::class.java)
}


class Presenter

class View<T>(val clz: Class<T>) {
    // 这是什么鬼, 现在就理解延迟初始化
    val presenter by lazy {
        println("newInstance ... ")
        clz.newInstance()
    }
    // 生成内部类以及Companion
    companion object {
        // 这是什么鬼
        // operator 将一个函数标记为重载一个操作符
        inline operator fun <reified T> invoke() = View(T::class.java)
    }
}

fun reifiedGenericTest() {
    val p1 = View<Presenter>().presenter
    val p2 = View(Presenter::class.java).presenter
    val p3 = View.Companion.invoke<Presenter>().presenter
    val flag1 = p1 === p2
    val flag2 = p1 === p3
    println("p1 === p2 ? $flag1") // false
    println("p1 === p3 ? $flag2") // false

    // 真的可以给class(JDK增加函数)
//    Gson().fromJsonMe<Presenter>("{}")
//
}