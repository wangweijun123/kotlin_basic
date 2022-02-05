package com.wangweijun.myapplication.zhangtao.unit05

/**
 * 第一种写法，使用object定义class，
 * 最简单的SimpleSington单例
 * 1 无懒加载
 * 2 无法传参
 */
object SimpleSington {

    fun doAction() {
        println("do action")
    }

}