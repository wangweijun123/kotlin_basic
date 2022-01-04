package com.wangweijun.myapplication.g1

import org.junit.Test

/**
 * 在kotlin中所有类都是继承Any
 */
class ClassAndObj {
    @Test
    fun main() {
        // .. 等价于 perator fun rangeTo(other: Int): IntRange
        val diceRange = 1..6
        // random 是扩展函数
        val random = diceRange.random()
        println("random = $random")
    }

    @Test
    fun main2() {
        val dice = Dice(10)
        println(dice.sides)
        val roll = dice.roll()
        println("roll 1 num : $roll")
        println("roll 2 num : ${dice.roll()}")
    }
}

class Dice(var sides: Int) {

    fun roll(): Int{
        val intRange = 1..sides
        val random = intRange.random()
        return random
    }
}