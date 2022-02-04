package com.wangweijun.myapplication.zhangtao.unit04

import kotlin.system.exitProcess

class CalculatorV2 {

    fun calculator2() {
        val arrayOf = arrayOf("1 + 1", "exit")
        var index = -1
        while(true) {
            index ++
            // 第一步，读取输入命令；
            val input = readLineFromArray2(arrayOf, index) ?: continue
//            if (input == null) continue

            // 第二步，判断命令是不是exit，如果是则直接退出程序；
            if (input == "exit") exitProcess(0)

            // 第三步，解析算式，分解出“数字”与“操作符”：“1”“+”“2”；
            val inputList = input.split(" ")
            // 第四步，根据操作符类型，算出结果：3；
            val result = calculate2(inputList)

            // 第五步，输出结果：1 + 2 = 3；
            if (result == null) {
                println("输入格式不对")
                continue
            } else {
                println("$input = $result")
            }

            // 第六步，进入下一个while循环。
        }
    }

    fun start() {

    }

    private fun readLineFromArray2(data: Array<String>, index: Int) = data[index]

    // 具体计算逻辑
    private fun calculate2(inputList: List<String>): Int? {
        if (inputList.size != 3) return null

        // 第七步，取出数字和操作符
        var left = inputList[0].toInt()
        var operation = Operation.valueOf(inputList[1])
        var right = inputList[2].toInt()

        // 第八步，根据操作符的类型，执行计算
        return when(operation) {
            Operation.ADD -> left + right
            Operation.MINUS -> left - right
            Operation.MULTI -> left * right
            Operation.DIVI -> left / right
        }
    }

    enum class Operation(val value: String) {
        ADD("+"), MINUS("-"), MULTI("*"), DIVI("/")
    }
}