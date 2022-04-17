package com.wangweijun.myapplication.zhangtao.unit05

import com.wangweijun.myapplication.zhangtao.unit05.last.PigManager
import org.junit.Test

class ObjectTest {
    interface A {
        fun funA()
    }

    interface B {
        fun funB()
    }

    abstract class Man {
        abstract fun findMan()
    }

    @Test
    fun main() {
    // 这个匿名内部类，在继承了Man类的同时，还实现了A、B两个接口
        val item = object : Man(), A, B {
            override fun funA() {
                TODO("Not yet implemented")
            }

            override fun funB() {
                TODO("Not yet implemented")
            }

            override fun findMan() {
                TODO("Not yet implemented")
            }

        }
    }


    @Test
    fun testSingleton() {
        // 反编译 UserManager.INSTANCE.login();
        UserManager.login()
    }

    @Test
    fun testGetUser() {
        val user = UserManager.getUser()
        val user2 = UserManager.getUser()
        println("user === user2 ? ${user === user2}")
    }

    @Test
    fun testSingleton2() {
        PPPerson.InnerSingleTon.foo()
        PPPerson.InnerSingleTon2.foo2()
    }
    @Test
    fun testSingleton3() {
        val uuuser = UUUser.create("dx")
    }

    @Test
    fun testSingleton4() {
        val uuuser1 = UserManager.uuuser
        val uuuser2 = UserManager.uuuser
        println("uuuser1 == uuuser2 ? ${uuuser1 == uuuser2}")
    }

    @Test
    fun testSimpleSington() {
        // 其实是调用了 SimpleSington.INSTANCE.doAction();
        SimpleSington.doAction()
    }

    @Test
    fun testUserManager() {
        // 其实是调用了 SimpleSington.INSTANCE.doAction();
        UserManager2.getInstance("dx")
        PersonManager.getInstance("dx")
    }

    @Test
    fun testUserManager2() {
        PersonManager2.getInstance("dx")
        UserManager3.getInstance("dxx")
    }


}