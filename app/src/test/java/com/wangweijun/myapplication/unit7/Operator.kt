package com.wangweijun.myapplication.unit7

import org.junit.Test

/**
 * 集合操作
 */
fun <T, E> Iterable<T>.convert(action: (T) -> E): MutableList<E> {
    val list: MutableList<E> = mutableListOf()
    for (item in this) { // this 是迭代器
        list.add(action(item))
    }
    return list
}

data class User(var name: String)

    fun arrayTest() {
        val a = arrayOf("4", "0", "7", "i", "f", "w", "0", "9")
        val index = arrayOf(5, 3, 9, 4, 8, 3, 1, 9, 2, 1, 7)
        index.filter {
            it < a.size
        }.map {
            a[it]
        }.reduce { p1, p2 ->
            "$p1$p2"
        }.also {
            println("密码是 $it")
        }
    }

    fun myOperator() {
        var list = listOf(1, 2, 3, 4, 5)
        list.convert {
            it + 1
        }.forEach {
            print("$it ")
        }
    }


    /**
     * 作用域函数
     */
    fun functionScope() {
        // 还是要看kotlin源码以及注释
        val user = User("wangweijun")
        // let also run with 这些

        // let 与 run 都会返回闭包的执行的结果, 区别在于let有闭包参数,
        // 而run没有闭包参数,使用this 获取当前调用对象
        val r1 = user.let {
            it.name
        }
        println(r1)
        // 也可以这样写
        val r11 = user.let { xx ->
            xx.name
        }
        println(r11)
        val r2 = user.run {
            this.name
        }
        println(r2)

        // also apply
        println("also apply 函数测试")
        println("user = $user")
        val also = user.also {
            println("it = $it")
            it.name
        }
        println("also = $also")

        val takeIf = user.takeIf {
            it.name.startsWith("wang", true)
        }
        println("takeIf = $takeIf")

        val with = with(user) {
            this.name
        }
        println("with = $with")
    }

