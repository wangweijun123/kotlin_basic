package com.wangweijun.myapplication.zhangtao.unite14

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.junit.Test

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/03 6:20 下午
 * version: 1.0
 * desc   :
 */

suspend fun CoroutineScope.func4(num: Int): Double {
    delay(100L)
    return num.toDouble()
}

val f4: suspend CoroutineScope.(Int) -> Double = CoroutineScope::func4
// 这样的环境测试有问题
fun main() {
    GlobalScope.launch {
        println("result = ${f4(100)}")
    }
    Thread.sleep(2000)
}