package com.wangweijun.myapplication.mycoroutines

import kotlinx.coroutines.*

/**
 * 启动协程的三种方式
 * 1 runBlocking: T         用于执行协程任务:通常只用于启动最外层协程,从线程切换到携程
 * 2 launch: Job            用于执行协程任务
 * 3 async/await : Deferred 用于执行协程任务, 并得到结果
 *
 * 单个线程上运行多个协程,多个携程并发执行
 *
 */
fun launchTest() {
    // 调用runBlocking后, 后面的代码是不会执行,用于在线程启动最外层协程
    // 启动主协程, 从线程环境进入到协程环境
    runBlocking {
        //  main, id=1 , 说明这些协程全部运行主线程
        println("runBlocking ...  " + Thread.currentThread().name + ", id="+Thread.currentThread().id)
        // 主协程启动协程2
        val job2 = launch {
            //  main, id=1
            println("launch ...  " + Thread.currentThread().name + ", id="+Thread.currentThread().id)
            repeat(10) {
                println("挂起中 $it")
                delay(500L)
            }
        }
        // 主协程启动协程3
        val job3 = async {
            //  main, id=1
            println("async ...  " + Thread.currentThread().name + ", id="+Thread.currentThread().id)
            delay(500L)
            // 这里返回这个协程哦
            return@async "hi dx"
        }

        println("before job3.await()")
        // 协程3 await 会阻塞主协程
        println("job3的输出 " + job3.await())
        println("after job3.await()")

        delay(1300L)
        println("main:: 主线程等待中")
        job2.cancel()
        job2.join()
        println("main:: 主线程退出")
    }
    // //  main, id=1, 注意这里需要等待所有携程运行完线程才会退出
    println("launchTest ...  " + Thread.currentThread().name + ", id="+Thread.currentThread().id)
    println("这是最外层launchTest完成")
}

fun testGlobalScope() {
    // first params： CoroutineContext
    // 这里没有不能在junit里面测试
    GlobalScope.launch(Dispatchers.Main) {
        println("dddddddddddd")
    }
}

suspend fun fetchTwoDocs() {
    coroutineScope {
        launch {

        }
    }
}
