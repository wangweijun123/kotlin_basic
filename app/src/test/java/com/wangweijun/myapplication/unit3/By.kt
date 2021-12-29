package com.wangweijun.myapplication.unit3

interface Animal2 {
    fun bark()
}

class Dog2 : Animal2 {
    override fun bark() {
        println("wang wang")
    }
}

// 1 没有反射的动态代理类, 构造函数传入实际的class, 使用关键字 BY,调用的还是传入的对象的方法
// 2 如果代理class 复写了函数,会执行复写的方法
class Zomm2(animal2: Animal2) : Animal2 by animal2 {
    override fun bark() {
        println("Zomm2 Zomm2")
    }
}