package com.wangweijun.myapplication

data class User(var id: Int, var name: String)
// 数据data class 不能继承
//class VIP(var id: Int, var name: String) : User(id, name)