package com.wangweijun.myapplication.zhangtao.unit12

import org.junit.Test

class JavaConvertExample7 {


    // 泛型定义处              泛型使用处
//   ↓                      ↓
    fun <T> saveSomething(data: T) { // data 是可空类型  T 等价于 Any?
        val set = sortedSetOf<T>() // Java TreeSet
        // 范型没有限制的话，他是可空类型,所以需要判空
        data?.let {
            set.add(it)
        }
        println("set size=${set.size}")
    }

    // 定义范型时，最好定义是否可空类型
    // 泛型定义处              泛型使用处
//   ↓  不为空的任何类型                    ↓
    fun <T: Any> saveSomething2(data: T) { //
        val set = sortedSetOf<T>() // Java TreeSet
        set.add(data)
        println("set size=${set.size}")
    }

    /**
     * Any 是所有非空类型的根类型， Any? 是所有可空类型的根类型
     * @param data T
     */
    fun <T: Any?> saveSomething3(data: T) { // T: Any?, data是可空类型
        val set = sortedSetOf<T>() // Java TreeSet
        data?.let {
            set.add(data)
        }
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
        saveSomething3(null)
    }

    @Test
    fun testAny() {
//        saveAny(null)
        saveAny2(null)
    }
    fun saveAny(any: Any) { // 不为null的any，所以传null编译出错

    }

    fun saveAny2(any: Any?) {

    }

}