package com.wangweijun.myapplication.zhangtao.unit10

import org.junit.Test

class Main4 {


     open class TV {
         open fun turnOn(){}
    }

    class XiaoMiTV1 : TV() {
        override fun turnOn() {
            println("XiaoMiTV1 turnOn")
        }
    }

    class Controller<in T> {
        fun turnOn(tv: T){
//            tv.turnOn()
            println(tv!!::class.simpleName)
        }
    }

    fun foo(tv: TV) {}


    //                      需要一个小米电视1的遥控器
//                                ↓
    private fun buy(controller: Controller<XiaoMiTV1>) {
        val xiaoMiTV1 = XiaoMiTV1()
        // 打开小米电视1
        controller.turnOn(xiaoMiTV1)
    }

    private fun buy2(controller: Controller<TV>) {
        val tv = TV()
        controller.turnOn(tv)
    }

    @Test
    fun main() {
// 要求父类，可以传入子类
        foo(XiaoMiTV1())
    }

    @Test
    fun main2() {
//                             实参
//                              ↓
        val controller = Controller<TV>()
        // 使用in就可以使用实参Controller<TV>传递Controller<XiaoMiTV1>
        buy(controller)
    }

    @Test
    fun main3() {
        /*val controller1 = Controller<TV>()
        buy2(controller1)*/
//                             实参
//                              ↓
        val controller = Controller<XiaoMiTV1>()
        // 使用in就可以使用实参Controller<TV>传递Controller<XiaoMiTV1>
//        buy2(controller) // build error
    }
    @Test
    fun modifyMemory() {
        val num  = 1
        if (num == 1) {
            println("not modify num = $num")
        } else {
            println("modify num = $num ")
        }

        val flag = true
        if (flag) {
            println("is true")
        } else {
            println("is false")
        }
    }

}