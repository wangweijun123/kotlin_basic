package com.wangweijun.myapplication.zhangtao.unit04.last.v1

import org.junit.Test
import kotlin.system.exitProcess

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/29 14:43
 * version: 1.0
 * desc   :
 */


class LastCalculatorV1 {
    val tipInfo = """
    --------------------------------------
    使用说明：
    1. 输入 1 + 1，按回车，即可使用计算器；
    2. 注意：数字与符号之间要有空格；
    3. 想要退出程序，请输入：
    exit-------------------------------------
""".trimIndent()

    val data: Array<String> = arrayOf("1 + 3", "exit")
    var firstEntry = true
    var index = -1
    @Test
    fun testV1() {
        println(tipInfo)
        while (true) {
            val input = readLineByMe() ?: continue // readLineByMe()如果返回null，执行continue
            println("input = ${input}")
//            if (input == null) {
//                println("input is null continue")
//                continue
//            }
            if (input == "exit") {
                println("input is exit ")
                exitProcess(0)
            }
            val inputList = input.split(" ")
            val result = calculate(inputList)
            if (result == null) {
                println("输入的格式不对")
                continue
            } else {
                println("$input = $result")
            }
        }
    }

    private fun calculate(inputList: List<String>): Int? {
        if (inputList.size != 3) return null
        // "1 + 3"
        val left = inputList[0].toInt()
//        val operation = Operation.valueOf(inputList[1]) // 抛出异常
        val operation = Operation.realValueOf(inputList[1])
        val right = inputList[2].toInt()
        return when (operation) { // when表达式与 enum 完备，不需要else,后续增加枚举值，编译会出错
            Operation.ADD -> left + right
            Operation.MINUS -> left + right
            Operation.MULTI -> left + right
            Operation.DIVI -> left + right
        }
    }


    private fun readLineByMe(): String? {
        if (firstEntry) {
            firstEntry = false
            return null
        }
        index ++
        return data[index]
    }

    enum class Operation(val value: String) {
        ADD("+"),
        MINUS("-"),
        MULTI("*"),
        DIVI("/");

        companion object {
            fun realValueOf(value: String): Operation {
                values().forEach {
                    if (value == it.value) {
                        return it
                    }
                }
                return ADD
            }
        }
    }
}