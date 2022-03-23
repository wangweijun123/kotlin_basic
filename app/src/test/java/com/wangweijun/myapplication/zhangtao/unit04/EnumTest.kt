package com.wangweijun.myapplication.zhangtao.unit04

import org.junit.Test
import java.lang.IllegalArgumentException

class EnumTest {

    @Test
    fun testEnum() {
        val dir = Direction.WEST
        when (dir) {
            Direction.NORTH -> println("north")
            Direction.SOUTH -> println("SOUTH")
            Direction.WEST -> println("WEST")
            Direction.EAST -> println("EAST")
        }

    }

    enum class Direction {
        NORTH, SOUTH, WEST, EAST
    }

    @Test
    fun testEnum2() {
        // 这个用字符串来稿异常
        val color = Color.valueOf(0xFF0000.toString())
        // 异常，兄弟
        when (color) {
            Color.RED -> println("RED")
            Color.GREEN -> println("GREEN")
            Color.BLUE -> println("BLUE")
        }

    }

    enum class Color(val rgb: Int) {
        RED(0xFF0000),
        GREEN(0x00FF00),
        BLUE(0x0000FF)
    }

    @Test
    fun testOperation() {
        val values = Operation.values()
        values.forEach {
            println("it=$it, it.value=${it.value}, it.name=${it.name}")
            // 有问题
            /*val operation = Operation.valueOf(it.value)
            println("operation = $operation")*/
        }
        val operation = Operation.valueOf("+")
        when (operation) {
            Operation.ADD -> println("add")
            Operation.MINUS -> println("MINUS")
            Operation.MULTI -> println("MULTI")
            Operation.DIVI -> println("DIVI")
            else -> println("not all ")
        }
    }

    @Test
    fun testOperation2() {
        val operation = Operation.valueOf("+")
        when (operation) {
            Operation.ADD -> println("add")
            Operation.MINUS -> println("MINUS")
            Operation.MULTI -> println("MULTI")
            Operation.DIVI -> println("DIVI")
            else -> println("not all ")
        }
    }
    enum class Operation(val value: String) {
        ADD("+"),
        MINUS("-"),
        MULTI("*"),
        DIVI("/");

        companion object {
            fun valueOf(myValue: String): Operation? {
                val values = values()
                for ((index, item) in values.withIndex()) {
                    println("index = $index, item=$item, item.name = ${item.name}, item.value = ${item.value}")
                }
                return values[0]
            }
        }
    }

    @Test
    fun testCardDisplayStatus() {
        try {
            val status = CardDisplayStatus.valueOf("xx")
            println("status = $status ")
        }catch (ex : IllegalArgumentException) {
            println("异常了")
            ex.printStackTrace()
        }

    }

    enum class CardDisplayStatus(val value: String, val display: String) {
        PROCESSING("PROCESSING", "PROCESSING"), PROCESSED("PROCESSED", "PROCESSED"), SHIPPED("SHIPPED", "SHIPPED"),
        SUSPENDED("SUSPENDED", "SUSPENDED"), TERMINATED("TERMINATED", "TERMINATED"), ACTIVE("ACTIVE", "ACTIVE"),
        LOCKED("LOCKED", "LOCKED"), FREEZED("FREEZED", "FROZEN"), REPLACEMENT("REPLACEMENT", "REPLACEMENT"),
        CLOSED("CLOSED", "TERMINATED");

        companion object {
            fun valueOf(value: String): CardDisplayStatus? = values().firstOrNull { it.value == value } ?: PROCESSING

//            fun valueOf(value: String): CardDisplayStatus? {
//                val result = values().firstOrNull {
//                    it.value == value
//                } ?: PROCESSING
//                return result
//            }
        }
    }


    @Test
    fun testMyStatus() {
        try {
            val status = MyStatus.valueOf("SUCCESS")
            println("status = $status ")
        }catch (ex : IllegalArgumentException) {
            println("异常了")
            ex.printStackTrace()
        }

    }

    enum class MyStatus(val value:String) {
        SUCCESS("SUCCESS"), // 枚举值value 必须是大写，而且需要一致
        FAILIED("FAILED2")
    }
}