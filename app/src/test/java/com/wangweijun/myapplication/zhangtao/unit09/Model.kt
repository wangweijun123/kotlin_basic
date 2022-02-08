package com.wangweijun.myapplication.zhangtao.unit09

class Model {
    val data: List<String> by ::_data
    private val _data = mutableListOf<String>()
    fun load() {
        _data.add("Hello")
    }
}