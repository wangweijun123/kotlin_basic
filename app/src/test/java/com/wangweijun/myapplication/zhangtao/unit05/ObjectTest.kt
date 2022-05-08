package com.wangweijun.myapplication.zhangtao.unit05

import com.wangweijun.myapplication.zhangtao.unit05.last.LastUserManager
import com.wangweijun.myapplication.zhangtao.unit05.last.PigManager
import org.junit.Assert
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
                println("funA")
            }

            override fun funB() {
                println("funB")
            }

            override fun findMan() {
                println("findMan")
            }
        }
        item.findMan()
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
        Assert.assertEquals(user, user2)
    }

    @Test
    fun testSingleton2() {
        PPPerson.InnerSingleTon.foo()
        PPPerson.InnerSingleTon2.foo2()
    }
//    @Test
    fun testSingleton3() {
        val uuuser = UUUser.create("dx")
        val uuuser2 = UUUser.createJvmStatic("dx")
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
        val instance = UserManager2.getInstance("dx")
        println("instance = $instance")
        Assert.assertEquals(UserManager2.getInstance("dx"), UserManager2.getInstance("dx"))
        PersonManager.getInstance("dx")
    }

    @Test
    fun testUserManager2() {
        PersonManager2.getInstance("dx")
        UserManager3.getInstance("dxx")
    }

    @Test
    fun testUserManagerLast() {
//        val userManager = LastUserManager("xx")
        val userManager = LastUserManager.getInstance("xxx")
    }

    @Test
    fun testUserManagerLast2() {
        val instance1 = PersonManagerLast.getInstance("xxx")
        val instance2 = PersonManagerLast.getInstance("xxx")
        println("name = ${instance1.name}")
        Assert.assertEquals(instance1, instance2)
    }
}