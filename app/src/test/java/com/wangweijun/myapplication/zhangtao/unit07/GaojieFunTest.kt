package com.wangweijun.myapplication.zhangtao.unit07

import org.junit.Test

class GaojieFunTest {
    fun add(a: Int, b: Int): Float {
        return (a+b).toFloat()
    }

    @Test
    fun testGaojie() {
        // :: 函数引用
        val function: (Int, Int) -> Float = ::add
        println(function(2, 3))
    }

    class MyUser(var name: String, var text: String)

    @Test
    fun testUser0() {
//        var myUser: MyUser? = MyUser("dx", "xxxx")
        var myUser: MyUser? = null
        println("外层 myuser = ${myUser}")
        myUser?.apply {
            println("name = ${name}")
            println("外层 myuser = ${this}")
            name = "xxx"
            myUser = null
        }

        println("最后 myuser = ${myUser}")
    }

    @Test
    fun testUser00() {
//        var myUser: MyUser? = MyUser("dx", "xxxx")
        var myUser: MyUser? = null
        println("外层 myuser = ${myUser}")
        myUser?.also {
            println("name = ${it.name}")
            println("外层 myuser = ${it}")
            myUser = null
        }

        println("最后 myuser = ${myUser}")
    }

    @Test
    fun testUser() {
        var myUser = MyUser("dx", "xxxx")
        if (myUser != null) {
            println("name = ${myUser.name}")
        }
        myUser?.apply {
            println("name = ${name}")
        }
    }

    fun MyUser.apply2(self: MyUser, block: (self: MyUser) -> Unit): MyUser{
        block(self)
        return this
    }

    fun MyUser.apply3(block: MyUser.() -> Unit): MyUser{
        block()
        return this
    }

    @Test
    fun testUser2() {
        var myUser = MyUser("dx", "xxxx")
        myUser?.apply2(self = myUser) { self: MyUser ->
            println("self.name = ${self.name}")
        }
        myUser.apply3 {
            println("self.name = ${name}")
        }
    }

}