package com.wangweijun.myapplication.zhangtao.unit12

import org.junit.Test

/**
 * 类型系统
 * String 不可为空的字符串
 * String? 可能为空的字符串
 * String! 不知道是不是可能为空。因此，在 Kotlin 当中，我们把 Java 未知可空性的类型叫做平台类型，比如：String!。
 * 所有 Java 当中的不确定可空性的类型，在 Kotlin 看来都是平台类型，用“!”来表示。
 */
class Main2 {

    @Test
    fun main() {
        val nullableString = NullJava.getNullableString(null)
        val notNullString = NullJava.getNotNullString("Hey,")

        // 这里是 String!, 不知道是不是为空类型
        val msg = NullJava.getMsg(null)
        val msg1 = NullJava.getMsg("Hello")
    }


// Kotlin代码
    @Test
    fun testPlatformType() {
        val nullableMsg: String? = NullJava.getNullableString(null)
        val notNullMsg: String = NullJava.getNotNullString("Hey,")
        // String! 可以当作String?， 也可以当作String，所以注意了，空指针问题？？？？
        val platformMsg1: String? = NullJava.getMsg(null)
        val platformMsg2: String = NullJava.getMsg("Hello")
    }

    @Test
    fun main3() {
        testNPE(null)
    }

    // Kotlin 开发的空安全：
// 1 Kotlin 的空安全调用语法“?.”   2  Kotlin 还提供了一个非空安全的调用语法“!!.”

    fun testNPE(msg: String?) {
//            非空断言
//              ↓
        val i = msg!!.length // 产生空指针异常，绝不使用非空断言“!!.”
    }

    @Test
    fun main4() {
        val javaConvertExample = JavaConvertExample()
        javaConvertExample.init()
        javaConvertExample.test()
    }

    @Test
    fun main5() {
        val javaConvertExample = JavaConvertExample()
        javaConvertExample.init()
        javaConvertExample.test2()
    }

    @Test
    fun main6() {
        var javaConvertExample: JavaConvertExample? = JavaConvertExample()
        javaConvertExample?.init()
        println("javaConvertExample.hashCode() = ${javaConvertExample?.hashCode()}")
        // it 原则上是同一个对象
        javaConvertExample?.let {
            println("it.hashCode() = ${it.hashCode()}")

            println("it == javaConvertExample ?  ${it == javaConvertExample}")
        }
    }

    @Test
    fun main7() {
        val example = JavaConvertExample5()
        example.init()
        example.test()
    }

    @Test
    fun main8() {
        val example = JavaConvertExample6()
//        example.init() // 不需要主动调用init()
        example.test()
        example.test()
    }

}