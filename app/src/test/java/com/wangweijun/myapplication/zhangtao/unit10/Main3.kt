package com.wangweijun.myapplication.zhangtao.unit10

import org.junit.Test

class Main3 {


     open class TV {
         open fun turnOn(){}
    }

    class XiaoMiTV1 : TV() {
        override fun turnOn() {
            println("XiaoMiTV1 turnOn")
        }
    }

    class Controller<T> {
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
//        val tv = TV()
        // 打开小米电视1
        controller.turnOn(xiaoMiTV1)
//        controller.turnOn(tv) // build error
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
        // 传入万能遥控器，报错
//        buy(controller)
    }


}