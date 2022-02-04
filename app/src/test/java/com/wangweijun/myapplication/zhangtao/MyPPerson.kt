package com.wangweijun.myapplication.zhangtao

class MyPPerson(val name: String, var age: Int) {
    // 这个jb看起来是一个属性，其实生成了一个方法
    // public final boolean isAdult()
    val isAdult
        get() = age >= 18

    // 这里呢,这里确实是一个属性, 但是 age变化不会引起isAdult2的值变化，
    // 反编译就知道了
    // 千万别这样写
    val isAdult2 = age >= 18

    var height = 100
}