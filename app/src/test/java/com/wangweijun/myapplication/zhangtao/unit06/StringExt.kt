package com.wangweijun.myapplication.zhangtao.unit06
// 被编译成了 这个文件的静态的方法， 并没有修改String的源代码哈
// 只是让你看起来好像是在String类中增加了一个成员方法
// 扩展函数与扩展属性必须定义在kotlin文件，不是在class当中

// kotlin

// 包不同就行，子包与父包是不同的
const val NUMBER_PHONE = 100 // ----> public static final int NUMBER_PHONE = 100;

val NUMBER_PHONE2 = 200 // --> private static final int NUMBER_PHONE2 = 200;

/**
 * 扩展函数 (public static final Character lastElement(String this) {} )
 * String : 接收者类型
 */
fun String.lastElement(): Char? {
    if (this.isEmpty()) {
        return null
    }
    return this[length - 1]
}

/**
 * 扩展属性
 */
val String.lastElement: Char?
    get() = if (isEmpty()) {
        null
    } else {
        get(length -1)
    }
