package com.wangweijun.myapplication.zhangtao.unit10

import org.junit.Test

class Main5 {

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


    @Test
    fun main() {
//                  找到一家肯德基
//                        ↓
        val kfc = Restaurant<KFC>()
// 需要普通饭店，传入了肯德基，编译器报错
        orderFood(kfc)
    }

    private fun findRestaurant(): Restaurant<*>? {
        return null
    }

    fun main2() {
        val restaurant = findRestaurant()
        //       注意这里
        //          ↓
        val food: Food? = restaurant?.orderFood() // 返回值是：Food或其子类
    }
}