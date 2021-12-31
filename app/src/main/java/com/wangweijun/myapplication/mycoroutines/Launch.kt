package com.wangweijun.myapplication.mycoroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 启动线程的三种方式
 * 1 runBlocking: T         用于执行协程任务:通常只用于启动最外层协程
 * 2 launch: Job            用于执行协程任务
 * 3 async/await : Deferred 用于执行协程任务, 并得到结果
 */
fun launchTest() {
    // 调用runBlocking后, 后面的代码是不会执行,用于在线程启动最外层协程
    // 启动主协程
    runBlocking {
        println("runBlocking ...  " + Thread.currentThread().name + ", id="+Thread.currentThread().id)
        // 主协程启动协程2
        val job2 = launch {
            println("launch ...  " + Thread.currentThread().name + ", id="+Thread.currentThread().id)
            repeat(10) {
                println("挂起中 $it")
                delay(500L)
            }
        }
        // 主协程启动协程3
        val job3 = async {
            println("async ...  " + Thread.currentThread().name + ", id="+Thread.currentThread().id)
            delay(500L)
            // 这里返回这个协程哦
            return@async "hi dx"
        }

        println("before job2.await()")
        // 协程3 await 会阻塞主协程
        println("job2的输出 " + job3.await())
        println("after job2.await()")

        delay(1300L)
        println("main:: 主线程等待中")
        job2.cancel()
        job2.join()
        println("main:: 主线程退出")
    }
    println("launchTest ...  " + Thread.currentThread().name + ", id="+Thread.currentThread().id)
    println("这是最外层launchTest完成")
}