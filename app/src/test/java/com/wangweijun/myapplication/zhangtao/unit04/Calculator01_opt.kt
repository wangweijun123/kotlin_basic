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
        val arrayOf = arrayOf("1 + 1", "exit")
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
        val operation = Operation.valueOf(inputList[1])
        val right = inputList[2].toInt()

        return when (operation) {
            Operation.ADD -> left + right
            Operation.MINUS -> left - right
            Operation.MULTI -> left * right
            Operation.DIVI -> left / right
        }
    }

    enum class Operation(val value: String) {
        ADD("+"),
        MINUS("-"),
        MULTI("*"),
        DIVI("/")
    }

    private fun readLineFromArray2(data: Array<String>, index: Int) = data[index]
}
