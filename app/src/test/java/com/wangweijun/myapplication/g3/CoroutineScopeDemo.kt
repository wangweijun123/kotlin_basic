package com.wangweijun.myapplication.g3

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CoroutineScopeDemo {
}

class MainScopeDemo {
    @Test
    fun testCoroutine() {
        runBlocking {
            launch { //启动一个新的协程并继续
                delay( 1000L ) // 1秒的非阻塞延迟（默认时间单位是ms）
                println ( " World! " ) //延迟后打印
            }
            println ( " Hello " ) //主协程继续，而前一个协程被延迟
        }
    }


}