package com.wangweijun.myapplication.zhangtao.unit10

import org.junit.Test

/**
 * in, out
 * 就可以使用Controller来替代Controller，也就是说，Controller是Controller的子类
 *
 */
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

    class Controller2<in T> {
        fun turnOn(tv: T){
            println(tv!!::class.simpleName)
        }
    }



    //                      需要一个小米电视1的遥控器
//                                ↓
    private fun buy(controller: Controller<XiaoMiTV1>) {
        val xiaoMiTV1 = XiaoMiTV1()
//        val tv = TV()
        // 打开小米电视1
        controller.turnOn(xiaoMiTV1)
//        controller.turnOn(tv) // build error
    }

    // 加in之后，居然可以传 Controller<TV>对象
    private fun buy2(controller: Controller<in XiaoMiTV1>) {
        val xiaoMiTV1 = XiaoMiTV1()
        controller.turnOn(xiaoMiTV1)
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
//        val controller = Controller<TV>()
        // 传入万能遥控器，报错

        val controller = Controller<XiaoMiTV1>() // 需要什么传什么，这样是ok的
        buy(controller)
    }


    @Test
    fun main3() {
        // 需要controller: Controller<in XiaoMiTV1>，传入Controller<TV>() ok
        val controller = Controller<TV>()
        buy2(controller)
    }
}