package com.wangweijun.myapplication.zhangtao.unit12.last

import org.junit.Assert
import org.junit.Test

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/05/16 23:36
 * version: 1.0
 * desc   : Any? 于 Any, Any 是所有kotlin类的根类型，Nothing是所有类的子类型
 *  Any? 是 Any的父亲大人
 */
class TestType {
    // 可空注解
    fun test(): Any? {
        return null
    }

    fun test1(): Any? {
        return null
    }

    // 不可空注解
    fun test2(): Any {
        return 1
    } //    @Test
    //    public void testNull() {
    //       Object o = test2();
    //       System.out.println(o);
    //        Object o1 = test();
    //        System.out.println(o1);
    //    }


    fun funUnit(): Unit { }

    fun funUnit1(): Unit { return Unit }
    // 单例： object定义的class， 直接返回类名,
    fun getObjectClass(): OjectClass = OjectClass

    @Test
    fun testGetObjectClass() {
        val objectClass = getObjectClass()
        val objectClass2 = getObjectClass()
        Assert.assertEquals(objectClass, objectClass2)
    }

    val f: () -> Unit = { println("x")}

    @Test
    fun testF() {
        f()
    }


    //       函数返回值类型是Int，实际上却抛出了异常，没有返回Int
//                ↓       ↓
    fun calculate(): Int = throw NotImplementedError() // 不会报错

    //       函数返回值类型是Any，实际上却抛出了异常，没有返回Any
//                ↓       ↓
    fun calculate1(): Any = throw Exception() // 不会报错

    //       函数返回值类型是Unit，实际上却抛出了异常，没有返回Unit
//                 ↓       ↓
    fun calculate2(): Unit = throw Exception() // 不会报错

    fun calculate5(): Nothing = throw Exception() // 不会报错

    @Test
    fun flowCode() {
        val a = 1
        println("calculate5 before")
        calculate5() // 抛出异常，底下的代码不会执行
        println("calculate5 after")
        val b = 5
    }

    // 这是一个无法调用的函数，因为找不到合适的参数
    fun show(msg: Nothing) {
    }

    fun testNothing() {
//        show(null) // 报错
        // throw Exception() 他是表达式，返回Nothing对象
        show(throw Exception()) // 虽然不报错，但方法仍然不会调用
    }


    fun f1(): Unit? { return null } // 通过

    fun f2(): Unit? { return Unit } // 通过

    fun f3(): Unit? { throw Exception() } // 通过

//    fun f4(): Unit? { } // 报错，缺少return


    val ff: (String) -> Unit = ::println




}