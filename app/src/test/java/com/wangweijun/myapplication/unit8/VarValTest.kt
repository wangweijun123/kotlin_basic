package com.wangweijun.myapplication.unit8

/**
 * var val 本质区别 val 定义的变量没有set函数
 *
 */
class Hello {
    var str: String? = null // private Ljava/lang/String; str
        get() {
            println("get ...")
            return field
        }
        set(value) {
            println("set ...")
            field = value
        }

    val age: Int = 100 // private final I age()
        get() {
            println("get age...")
            return field
        }
        // val 的属性是没有set函数
        /*set(value) {
            println("set ...")
            field = value
        }*/
}

/**
 * birthYear 与 age 也是成员变量
 */
class Person(var birthYear: Int) {
    val age: Int
        get() {
            return 100 - birthYear
        }

    fun oneYearLater() {
        birthYear --
    }
}