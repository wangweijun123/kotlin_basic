package com.wangweijun.myapplication.zhangtao.last

interface DXBehavior {
    // 接口内可以有属性 , 不能设置初始值
    val canWalk: Boolean

    // 接口有默认实现
    fun walk() {
        if (canWalk) {
            // do something
            println("i can walk")
        } else {
            println("i can not walk")
        }
    }
}