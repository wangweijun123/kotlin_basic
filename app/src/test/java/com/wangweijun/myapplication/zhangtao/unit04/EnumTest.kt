package com.wangweijun.myapplication.zhangtao.unit04

import org.junit.Test

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
        val color = Color.valueOf(0xFF0000.toString())
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

}