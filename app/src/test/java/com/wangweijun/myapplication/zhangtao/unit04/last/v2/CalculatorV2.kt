package com.wangweijun.myapplication.zhangtao.unit04.last.v2

import org.junit.Test
import kotlin.system.exitProcess

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/10 12:36 下午
 * version: 1.0
 * desc   :
 */
class CalculatorV2 {
    private val help = """
--------------------------------------
使用说明：
1. 输入 1 + 1，按回车，即可使用计算器；
2. 注意：数字与符号之间要有空格；
3. 想要退出程序，请输入：exit
--------------------------------------""".trimIndent()
    @Test
    fun testCC() {
        val calculatorV2 = CalculatorV2()
        calculatorV2.start()
    }

    fun start() {
        val arrayOf = arrayOf("1 + 3", "exit")
        var index = -1
        println(help)
        while (true) {
            index ++
            // 第一步，读取输入命令；
            val input = readLineFromArray2(arrayOf, index) ?: continue
            println("$input")
            if (input == "exit") exitProcess(0)

            val result = calculate(input)

            if (result == null) {
                println("输入格式不对")
                continue
            } else {
                println("$result")
            }
        }
    }

    private fun calculate(input: String): String? {
        val expression = parseExpression(input) ?: return null

        val left = expression.left
        val operate = expression.operate
        val right = expression.right
//        val operation = Operation.valueOf(inputList[1]) // 自带的方法有问题的

        return when (operate) {
            Operation.ADD -> addString(left, right)
            Operation.MINUS -> minusString(left, right)
            Operation.MULTI -> multiString(left, right)
            Operation.DIVI -> diviString(left, right)
        }
    }

    private fun parseExpression(input: String): Expression? {
        val operator = parseOperator(input) ?: return null
        val list = input.split(operator.value)
        if (list.size != 2) return null
        return Expression(
            left = list[0].trim(),
            operate = operator,
            right = list[1].trim()
        )
    }

    private fun parseOperator(input: String): Operation? {
        Operation.values().forEach {
            println("$it , ${it.value}")
            if (input.contains(it.value)) {
                return it
            }
        }
        return null
    }

    enum class Operation(val value: String) {
        ADD("+"),
        MINUS("-"),
        MULTI("*"),
        DIVI("/");

        companion object {
            fun realValueOf(value: String): Operation? {
                values().forEach {
                    if (value == it.value) {
                        return  it
                    }
                }
                return ADD
            }
        }
    }

    data class Expression(
        val left: String,
        val operate: Operation,
        val right: String
    )

    private fun readLineFromArray2(data: Array<String>, index: Int) = data[index]

    private fun addString(left: String, right: String): String {
        val result = left.toInt() + right.toInt()
        return result.toString()
    }

    private fun minusString(left: String, right: String): String {
        val result = left.toInt() - right.toInt()
        return result.toString()
    }

    private fun multiString(left: String, right: String): String {
        val result = left.toInt() * right.toInt()
        return result.toString()
    }

    private fun diviString(left: String, right: String): String {
        val result = left.toInt() / right.toInt()
        return result.toString()
    }

    private fun shouldExit(input: String): Boolean {
        return input == "exit"
    }
}