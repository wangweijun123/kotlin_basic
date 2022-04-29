import org.junit.Test
import kotlin.system.exitProcess

val help = """
--------------------------------------
使用说明：
1. 输入 1 + 1，按回车，即可使用计算器；
2. 注意：数字与符号之间要有空格；
3. 想要退出程序，请输入：exit
--------------------------------------""".trimIndent()
class Main {
    @Test
    fun testCC() {
       main()
    }

    fun main() {
        val arrayOf: Array<String> = arrayOf("1 + 3", "exit")
        var index = -1

        while (true) {
            println(help)
            index ++
            // 第一步，读取输入命令；
            val input = readLineFromArray2(arrayOf, index) ?: continue

            if (input == "exit") exitProcess(0)

            val inputList = input.split(" ")
            val result = calculate(inputList)

            if (result == null) {
                println("输入格式不对")
                continue
            } else {
                println("$input = $result")
            }
        }
    }

    private fun calculate(inputList: List<String>): Int? {
        if (inputList.size != 3) return null

        val left = inputList[0].toInt()
        //                           ①
        //                           ↓
        val operation = Operation.realValueOf(inputList[1])
//        val operation = Operation.valueOf(inputList[1]) // 自带的方法有问题的
        val right = inputList[2].toInt()
        // enum有问题
        return when (operation) {
            Operation.ADD -> left + right
            Operation.MINUS -> left - right
            Operation.MULTI -> left * right
            Operation.DIVI -> left / right
            else -> {-1}
        }
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

    private fun readLineFromArray2(data: Array<String>, index: Int) = data[index]

    @Test
    fun testEnum() {
        // 正确,Kotlin 提供的 valueOf() 就是用于解析“枚举变量名称”的。
        val addOp = Operation.valueOf("ADD")
        // 报错
        val addOp2 = Operation.valueOf("+")
    }

    @Test
    fun testEnumValueOf() {
        val addOp = OperationLast.realValueOf("+")
    }

    enum class OperationLast(val value: String) {
        ADD("+"),
        MINUS("-"),
        MULTI("*"),
        DIVI("/");

        companion object {
            fun realValueOf(value: String): OperationLast? {
                values().forEach {
                    if (value == it.value) {
                        return it
                    }
                }
                return null
            }
        }
    }

}
