package com.wangweijun.myapplication.zhangtao.unit06.last

import android.app.Person
import org.junit.Test

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/05/04 22:57
 * version: 1.0
 * desc   :
 */
class DuanxiaHelper {
    private fun walkOnFoot() {
        println("用脚走路")
    }
    // 只能使用被扩展类的共有属性，其实很简单，
    val DuanxiaPerson.isAdult: Boolean
        get() = age >= 18
    // 扩展定义在一个类的内部，外部类无法使用这个扩展函数
    fun DuanxiaPerson.walk() {
        // 调用了Helper的私有方法
        walkOnFoot()
    }

//    @Test
    fun test() {
        val person = DuanxiaPerson()
        person.walk()
    }
}