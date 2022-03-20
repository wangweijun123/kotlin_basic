package com.wangweijun.myapplication.zhangtao.unit09

import org.junit.Test

/**
 * 阻止外部对集合的修改
 * 方式1：定义不可变的变量暴露给外部，内部使用可变的集合委托 ::
 * 方式2: 自定义 get()，好像也差不多
 *
 */
class Model {
    // 阻止外部对集合的修改，使用list, 内部通过其他属性(_data可变列表)直接委托 ::
    val data: List<String> by ::_data
    private val _data = mutableListOf<String>()
    fun load() {
        _data.add("Hello")
    }
}

class Model2 {
    val data: List<String>
        get() = _data // 自定义get
    private val _data: MutableList<String> = mutableListOf()

    fun load() {
        _data.add("hello")
    }
}


class Model3 {
    private val data: MutableList<String> = mutableListOf()

    fun load() {
        data.add("Hello")
    }

    // 变化在这里
    fun getData(): List<String> = data
}

class Main {
    @Test
    fun testModel() {
        val model = Model()
        println("Before:${model.data}")
        val data = model.data
        (data as? MutableList)?.add("some data")
        println("After:${model.data}")
    }

    @Test
    fun testModel2() {
        val model = Model2()
        println("Before:${model.data}")
//        println("Before:${model.data}")

    }
}