package com.wangweijun.myapplication.zhangtao.basic.unit2

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/10 9:41 上午
 * version: 1.0
 * desc   :
 */
interface LastBehavior {
    // 接口居然有属性，反编译后他生成了一个方法
    var canWalk: Boolean // canWalk = true,不能设置初始值，失败
    // 接口居然有默认实现, 所以实现类可以不用实现,只需要实现没有实现的方法
    fun walk() {
        if (canWalk) {
            println("i can walk")
        } else {
            println("i can not walk")
        }
    }

    fun walk2()
}