package com.wangweijun.myapplication.zhangtao.unit09

import org.junit.Test

class Model {
    // 通过属性之间的直接委托
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
}