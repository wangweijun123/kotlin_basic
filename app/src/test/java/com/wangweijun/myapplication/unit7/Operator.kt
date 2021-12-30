package com.wangweijun.myapplication.unit7

/**
 * 集合操作
 */
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

fun <T, E> Iterable<T>.convert(action: (T) -> E): MutableList<E> {
    val list: MutableList<E> = mutableListOf()
    for (item in this) { // this 是迭代器
        list.add(action(item))
    }
    return list
}