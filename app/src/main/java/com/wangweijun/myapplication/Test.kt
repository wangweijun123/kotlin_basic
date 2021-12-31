package com.wangweijun.myapplication

/**
 * object Test  --->  public final Lcom/wangweijun/myapplication/Test
 *
 * 生成了一个单例：
 * field public static final INSTANCE:Lcom/wangweijun/myapplication/Test;
 * 私有构造函数
 * private constructor
 * 使用object定义的class 确实就是单例的写法
 *
 * 工具类就以用Object这个呗
 */
object Test {
    // 编译成了 public final sayMessage(Ljava/lang/String;)V
    fun sayMessage(msg: String) {
        println(msg)
    }

    // 编译成了 public static final jvmStaticTest(Ljava/lang/String;)V
    @JvmStatic
    fun jvmStaticTest(msg: String) {
        println(msg)
    }
}