package com.wangweijun.myapplication.zhangtao.unit12

import org.junit.Test

class JavaConvertExample7 {


    // 泛型定义处              泛型使用处
//   ↓                      ↓
    fun <T> saveSomething(data: T) { // data 是可空类型  T 等价于 Any?
        val set = sortedSetOf<T>() // Java TreeSet
        set.add(data)
        println("set size=${set.size}")
    }

    // 泛型定义处              泛型使用处
//   ↓  不为空的任何类型                    ↓
    fun <T: Any> saveSomething2(data: T) { // data 是可空类型  T 等价于 Any?
        val set = sortedSetOf<T>() // Java TreeSet
        set.add(data)
        println("set size=${set.size}")
    }

    @Test
    fun main() {
//                 泛型实参自动推导为String
//                        ↓
//        saveSomething("Hello world!")
        saveSomething(null) // runtime error
    }

    @Test
    fun main2() {
//                 泛型实参自动推导为String
//                        ↓
        saveSomething("Hello world!")
//        saveSomething2(null) // build error
    }

}