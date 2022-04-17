package com.wangweijun.myapplication.zhangtao.basic.unit2

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/10 9:52 上午
 * version: 1.0
 * desc   :
 */
class InnerAA {

    val nameXXX: String = "dx"
    fun foo() = 1
    // inner 关键字表明是真正的内部类，可以访问外部的成员了，持有外部的对象的引用
    inner class BB {
        val a = nameXXX
        val b = foo()
    }
}