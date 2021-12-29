package com.wangweijun.myapplication

/**
 * Kotlin 单例的最NB的写法
 */
class SingleKotlin private constructor(){
    fun test() {
        println("test SingleKotlin ")
    }

    companion object {
        fun get(): SingleKotlin {
            return Holder.instance
        }
    }

    object Holder {
        val instance = SingleKotlin()
    }
}