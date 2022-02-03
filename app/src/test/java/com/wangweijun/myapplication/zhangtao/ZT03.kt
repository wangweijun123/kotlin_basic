package com.wangweijun.myapplication.zhangtao

import org.junit.Test

class ZT03 {

    @Test
    fun testNullType() {

// kotlin 代码

// 用 val 定义可为空、不可为空的Long，并且赋值
        val a: Long = 1L
        val b: Long? = 2L

// 用 var 定义可为空、不可为空的Long，并且赋值
        var c: Long = 3L
        var d: Long? = 4L

// 用 var 定义可为空的Long，先赋值，然后改为null
        var e: Long? = 5L
        e = null

// 用 val 定义可为空的Long，直接赋值null
        val f: Long? = null

// 用 var 定义可为空的Long，先赋值null，然后赋值数字
        var g: Long? = null
        g = 6L

        val h = 1
        println(h)
    }

    interface Behavior {
        // 接口内可以有属性,实际上被编译成了方法--> boolean getCanWalk()
        var canWalk: Boolean

        // 接口有默认实现，实际上把实现去掉了, 生成了一个静态内部类
        fun walk() {
            if (canWalk) {
                // do something
                println("i can walk")
            } else {
                println("i can not walk")
            }
        }
    }

    class BehaviorImpl : ZT02.Behavior {
        // 重写接口的属性
        override var canWalk: Boolean = false
            get() {
                return field
            }

        override fun walk2() {
            println("walk2")
        }
    }

    @Test
    fun testInterface() {
        val behaviorImpl = BehaviorImpl()
        behaviorImpl.canWalk = true
        behaviorImpl.walk()
        behaviorImpl.walk2()
    }

}