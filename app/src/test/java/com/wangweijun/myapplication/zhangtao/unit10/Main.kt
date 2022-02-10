package com.wangweijun.myapplication.zhangtao.unit10

import org.junit.Test

class Main {

    class Controller<T : TV> {
        fun turnOn(tv: T){
            tv.turnOn()
        }

        fun turnOff(tv: T){
            tv.turnOff()
        }
    }

    abstract class TV {
        abstract fun turnOn()
        abstract fun turnOff()
    }

    class XiaoMiTV1 : TV() {
        override fun turnOn() {
            println("XiaoMiTV1 turnOn")
        }

        override fun turnOff() {
            println("XiaoMiTV1 turnOff")
        }
    }

    class XiaoMiTV2 : TV() {
        override fun turnOn() {
            println("XiaoMiTV2 turnOn")
        }

        override fun turnOff() {
            println("XiaoMiTV2 turnOff")
        }
    }

    @Test
    fun main() {
        val mi1Controller1 = Controller<XiaoMiTV1>()
        val mi1Controller2 = Controller<XiaoMiTV2>()
        val xiaoMiTV1 = XiaoMiTV1()
        val xiaoMiTV2 = XiaoMiTV2()
        mi1Controller1.turnOn(xiaoMiTV1)
        mi1Controller1.turnOff(xiaoMiTV1)

        mi1Controller2.turnOn(xiaoMiTV2)
        mi1Controller2.turnOff(xiaoMiTV2)
    }


}