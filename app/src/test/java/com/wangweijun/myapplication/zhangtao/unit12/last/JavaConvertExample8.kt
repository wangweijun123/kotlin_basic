package com.wangweijun.myapplication.zhangtao.unit12.last

import org.junit.Test

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/05/28 00:57
 * version: 1.0
 * desc   :
 */

class JavaConvertExample8 {
    private var name: String? = null
    private val name2: String? = null
    fun init() {
        name = "duanxia"
    }

    fun foo() {
        name = null;
    }

    @Test
    fun test() {
        init() // crash
        if (name != null) {
            // 几百行代码
            foo()
            //几百行代码
            val count = name!!.length
        }
    }

    @Test
    fun test2() {
        init()
        useParamName(name)
    }

    @Test
    fun test3() {
        // 不可变临时变量
        var _name = name
        if (_name != null) {
            val count = _name.length //

        }
    }

    @Test
    fun test4() {
        init()
        name?.let {
            println("it = $it")
            name = null
            // after name = null --> it = duanxia, name = null
            println("after name = null --> it = $it, name = $name")
        }
    }

    fun useParamName(name: String?) {
        if (name != null) { //
            foo2(name)
            val count = name.length // 以参数的形式传进来，ide能只能推断,不用加 ?.
            println("count = $count")
        }
    }

    // 在kotlin中，函数的参数是不可以变化，也不能加var/val，不能重新赋值
    // 与java不同
    private fun foo2(name: String?) {
//        name = "xxx" // build error
    }

    fun testName2() {
//        name2 = null // build error,because name2 is val
        if (name2 != null) {
            val count = name2.length // 不需要断言了
        }
    }
}