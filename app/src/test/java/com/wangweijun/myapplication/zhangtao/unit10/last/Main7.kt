package com.wangweijun.myapplication.zhangtao.unit10.last

import org.junit.Test

// Kotlin 源码当中的应用 Iterator<in T>   List<out E>

class Main7 {

    open class TV {
        open fun turnOn(){
            println("tv turnOn")
        }
        open fun turnOff(){
            println("tv turnOff")
        }
    }

    class XiaoMiTV1 : TV() {
        override fun turnOn() {
            println("XiaoMiTV1 turnOn")
        }

        override fun turnOff() {
            println("XiaoMiTV1 turnOff")
        }
    }

    class Controller<T: TV> {
        fun turnOn(tv: T){
            tv.turnOn()
        }

        fun turnOff(tv: T){
            tv.turnOff()
        }
    }


    @Test
    fun testControoler() {
        val controllerTV1 = Controller<XiaoMiTV1>()

        controllerTV1.turnOn(XiaoMiTV1())
//        controllerTV1.turnOn(TV()) // 编译不过

        println("#############")

        val controllerTV2 = Controller<TV>()
        controllerTV2.turnOn(XiaoMiTV1())
        controllerTV2.turnOn(TV())
    }



    fun <T: TV> turnOn(tv: T){
        tv.turnOn()
    }

    fun <T: TV> turnOff(tv: T){
        tv.turnOff()
    }

    @Test
    fun testfun() {
        turnOn(XiaoMiTV1())
        turnOn(TV())
    }

    open class Food {}

    class KFC: Food() {}
    class Restaurant<out T: Food> {
        fun orderFood(): T? { return null }
    }
    //                      这里需要一家普通的饭店，随便什么饭店都行
//                                     ↓
    fun orderFood(restaurant: Restaurant<Food>) {
        // 从这家饭店，点一份外卖
        val food = restaurant.orderFood()
    }

    fun orderKFC(restaurant: Restaurant<KFC>) {
        // 从这家饭店，点一份外卖
        val food = restaurant.orderFood()
    }

    @Test
    fun testFood() {
//                  找到一家肯德基
//                        ↓
        val kfc = Restaurant<KFC>()
// 需要普通饭店，传入了肯德基，编译器报错
        orderFood(kfc)
        orderKFC(kfc)
    }

    @Test
    fun testFood2() {
//                  找到一家肯德基
//                        ↓
        val kfc = Restaurant<Food>()
// 需要普通饭店，传入了肯德基，编译器报错
        orderFood(kfc)
//        orderKFC(kfc) // biuld error
    }


    //                   区别在这里
//                       ↓
    fun findRestaurant(): Restaurant<*> {
        return Restaurant<Food>()
    }

    fun main() {
        val restaurant = findRestaurant()
        //       注意这里
        //          ↓
        val food = restaurant.orderFood() // 返回值是：Food或其子类
    }
}