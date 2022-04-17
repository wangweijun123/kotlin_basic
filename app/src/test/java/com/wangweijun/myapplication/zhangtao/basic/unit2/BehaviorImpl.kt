package com.wangweijun.myapplication.zhangtao.basic.unit2

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/10 9:43 上午
 * version: 1.0
 * desc   :
 */
class BehaviorImpl : LastBehavior {
    override var canWalk: Boolean = true

    override fun walk2() {
        println("walk2")
    }
    // 当然可以复写
//    override fun walk() {
//        super.walk()
//    }
}