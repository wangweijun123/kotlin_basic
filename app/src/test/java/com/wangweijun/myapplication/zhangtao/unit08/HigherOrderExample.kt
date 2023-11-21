package com.wangweijun.myapplication.zhangtao.unit08

import org.junit.Assert
import org.junit.Test

// inline 加与不加，查看编译后kotlin字节码，然后反编译成java代码
// inline 会把代码块中的代码，直接替换到调用处 1 减少调用层级 2 少创建了匿名内部类
// inline 只用于修饰高阶函数
inline fun foo(block: () -> Unit) {
    println("code in function fooInline")
    block()
}

//    @Test
fun main() {
    var i = 0
    foo{
        i++
//            Assert.assertEquals(1, i)
    }
}
