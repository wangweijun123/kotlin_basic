package com.wangweijun.myapplication.zhangtao.unit12

import org.junit.Test

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/03/20 6:18 下午
 * version: 1.0
 * desc   :
 */

class JavaConvertExample {
    // 可空类型
    private var name: String? = null
    fun init() {
        name = "duanxia"
    }

    fun foo() {
        name = null;
    }

    fun test() {
        if (name != null) {
            // 几百行代码
            foo()
            //几百行代码
            val count = name!!.length // 死翘翘
        }
    }

    fun test2() {
        name?.let {
            foo()
            println("call foo() --> $name")
            println("first use let $it")

            println("second use let ${it.length}")
        }
    }

    // 这里就不需要加 断言 !!
    fun test3(name: String?) {
        if (name != null) {
            val cont = name.length
        }

        name?.let {
            val cont = name.length
        }
    }
}