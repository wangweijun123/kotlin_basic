package com.wangweijun.myapplication.zhangtao.unit07

import org.junit.Test

class GaojieFunTest {
    fun add(a: Int, b: Int): Float {
        return (a+b).toFloat()
    }
    val testADD: (Int,Int)->Int = { x,y -> x+y } //是正确的一样

    // 值推导出定义类型，注意这里的var
    var testADD2 = { x: Int, y: Int -> x + y } //这里也是正确的一样
// 定义推导出值类型，这里是重新赋值
//    test =  { x, y -> x + y }
    @Test
    fun testGaojie() {
        // :: 函数引用
        val function: (Int, Int) -> Float = ::add
        println(function(2, 3))

        val re = testADD(1, 3)
        println("re = $re")
        testADD2(2,3)
    }

    @Test
    fun testTuidao() {
        val tuidao = tuidao(3)
        when (tuidao) {
            is Int -> println("return int")
            is Boolean -> println("return boolean")
            else -> println("未知")
        }
    }
    // 返回任意类型
    private fun tuidao(num: Int): Any {
        if (num == 1) {
            return num
        } else if (num == 2){
            return true
        } else {
            return  "xxx"
        }
    }

    data class MyUser(var name: String, var text: String)

//    @Test
    fun testUser0() {
        var myUser: MyUser? = MyUser("dx", "xxxx")
//        var myUser: MyUser? = null
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
    fun testUserApply0() {
        var myUser: MyUser? = MyUser("dx", "xxxx")
        println("外层 myuser = ${myUser}")

        myUser?.myApply(myUser) { self: MyUser ->
            self.name = self.name + " i love"
        }
        println("修改过后 myuser = ${myUser}")
    }

    fun MyUser.myApply(self: MyUser, block: (self: MyUser) -> Unit): MyUser {
        block(self)
        return this
    }


    // myApply2函数 等价于 myApply3函数
    fun MyUser.myApply2(block: MyUser.() -> Unit): MyUser {
        block()
        return this
    }
    fun myApply3(myUser: MyUser, block: (myUser: MyUser) -> Unit): MyUser {
        block(myUser)
        return myUser
    }

    @Test
    fun testUserApply1() {
        var myUser: MyUser? = MyUser("dx", "xxxx")
        println("外层 myuser = ${myUser}")

        myUser?.myApply2 {
            this.name = this.name + " i love"
        }
        println("带接收者函数修改过后 myuser = ${myUser}")
    }

    /**
     * 带接收者的扩展函数 === 等驾于成员方法
     *
    class MyUser() {
        val name: String = ""
        val blog: String = ""

        fun myApply2() {
            // 成员方法可以通过 this 访问成员变量
            username.text = this.name
            website.text = this.blog
            image.setOnClickListener { gotoImagePreviewActivity(this) }
        }
    }
     */




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