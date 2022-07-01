package com.wangweijun.myapplication.zhangtao.unite13

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/06/19 19:14
 * version: 1.0
 * desc   :
 */
class Coroutine2 {

    fun test() {
        // 反编译runBlocking代码，创建来这个对象Continuation,调用getLoginInfo(Continuation)传入来Continuation对象
        runBlocking {
//            getLoginInfo()
            getLoginInfo2(100)
        }
    }

    // 1 编译后加了一个对象 Continuation 来作为回掉(这个参数在最后面)
    // 2 如果在普通函数里去调用是没有Continuation这个对象，所以不能调用
    // 3
//    suspend fun getLoginInfo() {
//        delay(5000)
//    }

    suspend fun getLoginInfo2(age: Int) {
        delay(5000)
    }
}