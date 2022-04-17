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
class SuspendDemo {

    @Test
    fun main() {
        println("main...start ...${getThreadInfo()}")
        runBlocking {
            println("runBlocking...${getThreadInfo()}")
            // val user 定义运行主线程，getUserInfo()挂起函数运行子线程
            // 这里有依赖，所以getUserInfo()与getFriendList()是同一个协程
            val user = getUserInfo()
            val friendList = getFriendList(user)
            val feedList = getFeedList(friendList)
            println("runBlocking...${getThreadInfo()}")
        }
        println("main...end ...${getThreadInfo()}")
    }


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
        println("getUserInfo...${getThreadInfo()}")
        // 指定线程池 Dispatchers.IO
        withContext(Dispatchers.IO) {
            println("getUserInfo...withContext start...${getThreadInfo()}")
            delay(1000L)
            println("getUserInfo...withContext end...${getThreadInfo()}")
        }
        println("getUserInfo...return ...${getThreadInfo()}")
        return "BoyCoder"
    }

    //挂起函数
// ↓
    suspend fun getFriendList(user: String): String {
        println("getFriendList...${getThreadInfo()}")
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
        println("getFriendList...${getThreadInfo()}")
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
}