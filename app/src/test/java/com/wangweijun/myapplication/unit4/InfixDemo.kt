package com.wangweijun.myapplication.unit4

sealed class CompareResult {
    object LESS : CompareResult() {
        override fun toString(): String {
            return "小于"
        }
    }
    object MORE : CompareResult() {
        override fun toString(): String {
            return "大于"
        }
    }
    object EQUAL : CompareResult() {
        override fun toString(): String {
            return "等于"
        }
    }
}

/**
 * 给Int增加vs函数而已
 */
fun Int.vs(num: Int): CompareResult{
    if (this - num < 0) {
        return CompareResult.LESS
    } else if (this - num > 0) {
        return CompareResult.MORE
    } else {
        return CompareResult.EQUAL
    }
}

/**
 * infix 中缀表达式
 */
infix fun Int.vs2(num: Int): CompareResult{
    if (this - num < 0) {
        return CompareResult.LESS
    } else if (this - num > 0) {
        return CompareResult.MORE
    } else {
        return CompareResult.EQUAL
    }
}

fun infixTest() {
    println(5.vs(6))
    println(7.vs(6))
    println(8.vs(8))
//    println(8 vs 8) build error
    // 两种写法
    println(8 vs2 8)
    println(8.vs2(8))
}