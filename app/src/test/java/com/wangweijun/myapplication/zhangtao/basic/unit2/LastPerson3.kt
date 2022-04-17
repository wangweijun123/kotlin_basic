package com.wangweijun.myapplication.zhangtao.basic.unit2

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/10 9:20 上午
 * version: 1.0
 * desc   :
 */
class LastPerson3(val name: String, var age: Int) {
    // 看起来是属性，实际上是一个函数，所以不会占用内存
    val isAdult
        get() = age > 18
}