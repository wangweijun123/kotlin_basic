package com.wangweijun.myapplication.zhangtao.unite15

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Test

suspend fun getUserInfo(): String {
    withContext(Dispatchers.IO) {
        delay(1000L)
    }
    return "BoyCoder"
}

class SuspendDemoLastxxx {

    @Test
    fun main2() {
        runBlocking {
            val user = getUserInfo()
            println("user = $user")
        }
    }


}