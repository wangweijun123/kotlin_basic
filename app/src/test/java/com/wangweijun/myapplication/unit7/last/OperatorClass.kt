package com.wangweijun.myapplication.unit7.last

import com.wangweijun.myapplication.unit7.convert
import org.junit.Test

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/05/08 11:07
 * version: 1.0
 * desc   :
 * run：调用一个函数，作用仅仅限于构建lambda方便一点
带receiver的run：调用一个带receiver的函数，把this传给这个函数
with：用第一个参数调用第二个带receiver的函数，把this设定为第一个参数
apply：带receiver的函数高阶函数，参数为带receiver的函数，接受一个对象，把这个对象作为this传给参数并调用，返回this
also：和apply类似，但是参数是带一个参数的函数，接受对象传给参数，其余和apply一样
let：和apply类似，但是返回值不是this，而是函数的返回
takeIf：带receiver的函数高阶函数，参数是一个判断函数，结果判断结果为真就返回this，否者null
takeUnless：和take相反
repeat：参数为次数和函数，for循环执行函数
 */
class OperatorClass {

    data class User(var name: String)

    @Test
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

    @Test
    fun functionScope2() {
//        val user = User("wangweijun")
                val user = null
        xxx(user)

    }

    fun xxx(user: User?) {
        val result = user?.let {
            user.name
        }
        println("result = $result")
    }


    /**
     * 作用域函数
     */
    @Test
    fun functionScope() {
        // 还是要看kotlin源码以及注释
        val user = User("wangweijun")
//        val user = null
        // let also run with 这些

        // let 与 run 都会返回闭包的执行的结果, 区别在于let有闭包参数,
        // 而run没有闭包参数,使用this 获取当前调用对象
        val r1 = user?.let {
            it.name
        }
        println(r1)
        // 也可以这样写
        val r11 = user.let { xx ->
            xx.name
        }
        println(r11)

        // 不带接收者的run,基本上无用
        val res = kotlin.run {
            user.name
        }
        println("res = $res")

        // 这是带接收者的T.run()
        val r2 = user.run {
            this.name
        }
        println(r2)

        // also apply
        println("also apply 函数测试")
        println("user = $user")
        val also = user.also {
            println("it = $it")
            it.name
        }
        println("also = $also")

        val takeIf = user.takeIf {
            it.name.startsWith("duan", true)
        }
        println("takeIf === user ? ${takeIf === user}")

        val with = with(user) {
            this.name
        }
        println("with = $with")

        user?.apply {
            println("this.name = ${this.name}")
        }
    }
}