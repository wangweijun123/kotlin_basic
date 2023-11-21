package com.wangweijun.myapplication.zhangtao.unite15

import kotlinx.coroutines.*
import org.junit.Test
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/03 10:38 下午
 * version: 1.0
 * desc   :
 */
class SuspendDemoLast {

    @Test
    fun mainRunBlocking() {
        println("main...start ...${getThreadInfo()}")
        runBlocking {
            // runBlocking 运行的代码也是在主线程
            println("runBlocking...${getThreadInfo()}")
            // val user 定义运行主线程，getUserInfo()挂起函数运行子线程，因为suspend修饰getUserInfo()是挂起函数
            //
            // 这里有依赖，所以getUserInfo()与getFriendList()是同一个协程
            // 需求，getUserInfo我需要子线程执行网络请求呀，我只能调用suspend挂起函数，
            // 挂起的意思什么？指定耗时代码运行在其他线程，其他线程运行完耗时代码后，
            // 挂起函数只能在协程中被调用, suspend 作用挂起(main线程 -> io线程)和恢复(io线程 -> main线程)
            // 协程的作用: 以同步的方式来写异步代码
            // 为什么运行到了getUserInfo()(getUserInfo函数的在io线程调用)，getFriendList()没有并发执行,因为
            // 底下的代码被包装成了一个callback,挂起点剩下的代码会留在之后执行
            // 挂起函数只能在协程中被调用: 是携程提供了环境: 就是Continuation还有上下文环境CoroutineContext
            // "=" 等号左边的定义在main 线程运行，"="右边的代码在 getUserInfo()在开启的新线程运行
            val user = getUserInfo()
            val friendList = getFriendList(user)
            val feedList = getFeedList(friendList)
            println("runBlocking...${getThreadInfo()}")
        }
        println("main...end ...${getThreadInfo()}")
    }

    @Test
    fun mainLaunch() {
        // main...start ..., tid = 13,tname = Test worker
        println("main...start ...${getThreadInfo()}")
        // launch 一个异步任务, 至始至终只有一个携程(所有代码都是运行这个携程里面)，只是这个携程里面的代码可以跑在不同的线程
        GlobalScope.launch {
            // 这里运行在新线程
            // runBlocking..., tid = 16,tname = DefaultDispatcher-worker-1 @coroutine#1
            println("launch...${getThreadInfo()}")
            // val user 定义运行主线程，getUserInfo()挂起函数运行子线程，因为suspend修饰getUserInfo()是挂起函数
            //
            // 这里有依赖，所以getUserInfo()与getFriendList()是同一个协程
            val user = getUserInfo()
            val friendList = getFriendList(user)
            val feedList = getFeedList(friendList)
            println("launch finished...${getThreadInfo()}")
        }
        // 如果不睡觉，下面的代码就会执行，主线程会退出，携程中的代码执行不完
        Thread.sleep(4000)
        println("main...end ...${getThreadInfo()}")
    }

//


// 代码段4
    /**
     * 挂起，只是将程序执行流程转移到了其他线程，主线程不会被阻塞。如果以上代码运行在 Android 系统，
     * 我们的 App 仍然可以响应用户的操作，主线程并不繁忙
     * @return String
     */
// delay(1000L)用于模拟网络请求

    //挂起函数
// ↓
    suspend fun getUserInfo(): String {
        // getUserInfo..., tid = 13,tname = Test worker @coroutine#1
        println("getUserInfo...${getThreadInfo()}")
        // 切换线程::: 指定这块耗时代码运行在线程池 Dispatchers.IO,因为他耗时
        // 如果不定义成suspend函数，你是切换不了线程的
        withContext(Dispatchers.IO) {
            // 指定这块代码运行在Dispatchers.IO tid = 16,tname = DefaultDispatcher-worker-1 @coroutine#1
            println("getUserInfo...withContext start...${getThreadInfo()}")
            delay(1000L)
            println("getUserInfo...withContext end...${getThreadInfo()}")
        }
        // 这句话只有当withContext{} 运行完才运行
        // getUserInfo...return ..., tid = 13,tname = Test worker @coroutine#1
        println("getUserInfo...return ...${getThreadInfo()}")
        return "BoyCoder"
    }

//    1. 一个是当前线程不会被阻塞，可以执行其他任务。
//    2.挂起点剩下的代码，会留到之后再执行。
    //挂起函数
// ↓
    suspend fun getFriendList(user: String): String {
        println("getFriendList...${getThreadInfo()}")

        // 控制协程执行的线程池
        withContext(Dispatchers.IO) {
            println("getFriendList...withContext start...${getThreadInfo()}")
            delay(1000L)
            println("getFriendList...withContext end...${getThreadInfo()}")
        }
        println("getFriendList...return ...${getThreadInfo()}")
        return "Tom, Jack"
    }

    //挂起函数
// ↓
    suspend fun getFeedList(list: String): String {
        println("getFeedList...${getThreadInfo()}")
        withContext(Dispatchers.IO) {
            println("getFeedList...withContext start...${getThreadInfo()}")
            delay(1000L)
            println("getFeedList...withContext start...${getThreadInfo()}")
        }
        println("getFeedList...return ...${getThreadInfo()}")
        return "{FeedList..}"
    }



// 代码段5

    /*fun func1(num: Int): Double {
        return num.toDouble()
    }
    *//*
    挂起函数的本质，就是 Callback。
    func1与func3唯一的区别
       ↓                         *//*
    suspend fun func3(num: Int): Double {
        delay(100L)
        return num.toDouble()
    }

    val f1: (Int) -> Double = ::func1
    val f2: suspend (Int) -> Double = ::func3*/

//    val f3: (Int) -> Double = ::func3 // 报错
//    val f4: suspend (Int) -> Double = ::func1 // 报错



    fun getThreadInfo() = ", tid = ${Thread.currentThread().id},tname = ${Thread.currentThread().name}"

    /**
     * 控制台输出带协程信息的log
     */
    fun logX(any: Any?) {
        println("""
================================
$any
Thread name:${Thread.currentThread().name}, tid:${Thread.currentThread().id}
================================""".trimIndent())
    }
// 代码段2

    // block: suspend CoroutineScope.() -> T
    // block 是一个挂起函数类型
    @Test
    fun main2() = runBlocking {
        val user = getUserInfo2()
        logX(user)
    }

    suspend fun getUserInfo2(): String {
        logX("Before IO Context.")
        withContext(Dispatchers.IO) { //指定线程池来执行
            logX("In IO Context.")
            delay(1000L)
        }
        logX("After IO Context.")
        return "BoyCoder"
    }

/*
输出结果：
================================
Before IO Context.
Thread:main @coroutine#1
================================
================================
In IO Context.
Thread:DefaultDispatcher-worker-1 @coroutine#1
================================
================================
After IO Context.
Thread:main @coroutine#1
================================
================================
BoyCoder
Thread:main @coroutine#1
================================
*/


// 代码段4

    //                          变化在这里
//                             ↓
    @Test
    fun main3() = runBlocking(Dispatchers.IO) {
        val user = getUserInfo2()
        logX(user)
    }

/*
输出结果：
================================
Before IO Context.
Thread:DefaultDispatcher-worker-1 @coroutine#1
================================
================================
In IO Context.
Thread:DefaultDispatcher-worker-1 @coroutine#1
================================
================================
After IO Context.
Thread:DefaultDispatcher-worker-1 @coroutine#1
================================
================================
BoyCoder
Thread:DefaultDispatcher-worker-1 @coroutine#1
================================
*/


// 代码段4

    //                          变化在这里
//                             ↓
    @Test
    fun main4() = runBlocking(Dispatchers.IO) {
        val user = getUserInfo()
        logX(user)
    }

/*
输出结果：
================================
Before IO Context.
Thread:DefaultDispatcher-worker-1 @coroutine#1
================================
================================
In IO Context.
Thread:DefaultDispatcher-worker-1 @coroutine#1
================================
================================
After IO Context.
Thread:DefaultDispatcher-worker-1 @coroutine#1
================================
================================
BoyCoder
Thread:DefaultDispatcher-worker-1 @coroutine#1
================================
*/


// 代码段6

    val mySingleDispatcher = Executors.newSingleThreadExecutor {
        Thread(it, "MySingleThread").apply { isDaemon = true }
    }.asCoroutineDispatcher()

    //                          变化在这里
//                             ↓
    @Test
    fun main5() = runBlocking(mySingleDispatcher) {
        val user = getUserInfo2()
        logX(user)
    }


/*
输出结果：
================================
Before IO Context.
Thread:MySingleThread @coroutine#1
================================
================================
In IO Context.
Thread:DefaultDispatcher-worker-1 @coroutine#1
================================
================================
After IO Context.
Thread:MySingleThread @coroutine#1
================================
================================
BoyCoder
Thread:MySingleThread @coroutine#1
================================
*/


// 代码段10
    @Test
    fun main6() = runBlocking {
        // 仅用于测试，生成环境不要使用这么简易的CoroutineScope
        val scope = CoroutineScope(Job())

        scope.launch {
            logX("First start!")
            delay(1000L)
            logX("First end!") // 不会执行
        }

        scope.launch {
            logX("Second start!")
            delay(1000L)
            logX("Second end!") // 不会执行
        }

        scope.launch {
            logX("Third start!")
            delay(1000L)
            logX("Third end!") // 不会执行
        }

        delay(500L)

        scope.cancel()

        delay(1000L)
    }

/*
输出结果：
================================
First start!
Thread:DefaultDispatcher-worker-1 @coroutine#2
================================
================================
Third start!
Thread:DefaultDispatcher-worker-3 @coroutine#4
================================
================================
Second start!
Thread:DefaultDispatcher-worker-2 @coroutine#3
================================
*/


// 代码段14

    @Test
    @OptIn(ExperimentalStdlibApi::class)
    fun main7() = runBlocking {
        // 注意这里
        val scope = CoroutineScope(Job() + mySingleDispatcher)

        scope.launch {
            // 注意这里
            logX(coroutineContext[CoroutineDispatcher] == mySingleDispatcher)
            delay(1000L)
            logX("First end!")  // 不会执行
        }

        delay(500L)
        scope.cancel()
        delay(1000L)
    }
/*
输出结果：
================================
true
Thread:MySingleThread @coroutine#2
================================
*/

    @Test
    fun main33() = runBlocking(Dispatchers.Default) {
        logX("runBlocking ...")
        val user = getUserInfo()
        logX(user)
    }
}