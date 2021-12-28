package com.wangweijun.myapplication

/**
 * object Test  --->  public final Lcom/wangweijun/myapplication/Test
 *
 * 生成了一个单例：
 * field public static final INSTANCE:Lcom/wangweijun/myapplication/Test;
 *
 * 确实就是单例的写法
 */
object Test {
    fun sayMessage(msg: String) {
        println(msg)
    }
}