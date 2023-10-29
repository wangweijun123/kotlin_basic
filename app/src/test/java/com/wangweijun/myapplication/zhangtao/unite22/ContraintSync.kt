package com.wangweijun.myapplication.zhangtao.unite22

import com.wangweijun.myapplication.mycoroutines.last.getLastThreadInfo
import kotlinx.coroutines.*
import org.junit.Test
import java.util.concurrent.Executors

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/07/03 22:21
 * version: 1.0
 * desc   :
 */
class ContraintSync {


// 代码段1

    /**
     * 代码中压根就没有并发执行的任务
     */
    @Test
    fun main() = runBlocking {
        var i = 0

        println("start -> ${getLastThreadInfo()}")
        // Default 线程池
        launch(Dispatchers.Default) {
            repeat(1000) {
                i++
                println("i++ 后 = $i -> ${getLastThreadInfo()}")
            }
            println("launch end -> ${getLastThreadInfo()}")
        }
        println("delay start -> ${getLastThreadInfo()}")
        delay(1000L)
        println("delay end -> ${getLastThreadInfo()}")
        println("i = $i")
    }



// 代码段2

    /**
     * i = 8775 有并发问题, 发现结果大概率不会是 10000
     */
    @Test
    fun main2() = runBlocking {
        var i = 0
        val jobs = mutableListOf<Job>()
        println("start -> ${getLastThreadInfo()}")
        // 重复十次
        repeat(10){
            val job = launch(Dispatchers.Default) {
                println("launch -> ${getLastThreadInfo()}")
                repeat(1000) {
                    i++
                }
            }
            println("add job -> ${getLastThreadInfo()}")
            jobs.add(job)
        }

        // 等待计算完成
        println("joinAll -> ${getLastThreadInfo()}")
        jobs.joinAll()

        println("i = $i   ${getLastThreadInfo()}") // i = 8775 有并发问题, 发现结果大概率不会是 10000
    }
/*
输出结果
i = 9972
*/

    /**
     * i = 8775 有并发问题, 发现结果大概率不会是 10000
     */
    // 多个协程并发执行, 其实是因为协程运行在线程之上，根本原因产生了多个线程
    @Test
    fun main3() = runBlocking {
        var i = 0
        val jobs = mutableListOf<Job>()

        // 重复十次
        repeat(10){
            val job = launch(Dispatchers.Default) { // 10个协程运行在线程池之上，i处于多线程环境中,所以有并发问题
                repeat(1000) {
                    i++
                }
            }
            jobs.add(job)
        }

        // 等待计算完成
        jobs.joinAll()

        println("i = $i")
    }


    // 代码段6
    /**
     * 10 个协程运行在线程池上(注意这里线程池中只有一个线程，所以并不会有并发问题)i = 10000
     */
    @Test
    fun main33() = runBlocking {
        val mySingleDispatcher = Executors.newSingleThreadExecutor {
            Thread(it, "MySingleThread").apply { isDaemon = true }
        }.asCoroutineDispatcher()

        var i = 0
        val jobs = mutableListOf<Job>()

        repeat(10) {
            val job = launch(mySingleDispatcher) {
                repeat(1000) { // 虽然是一个线程，但是还是并发执行
                    i++
                }
            }
            jobs.add(job)
        }

        jobs.joinAll()

        println("i = $i")
    }

/*
输出结果
i = 10000
*/
/*
输出结果
i = 9972
*/



// 代码段3

    @Test
    fun useSynchronized() = runBlocking {
        var i = 0
        val lock = Any() // 变化在这里

        val jobs = mutableListOf<Job>()

        repeat(10){
            val job = launch(Dispatchers.Default) {
                repeat(1000) {
                    // 变化在这里
                    synchronized(lock) {
//                        delay(100) // build error同步代码块中不能使用挂起函数
                        i++
                    }
                }
            }
            jobs.add(job)
        }

        jobs.joinAll()

        println("i = $i") // i = 10000
    }

/*
输出结果
i = 10000
*/


// 代码段12

    @Test
    fun main4() = runBlocking {
        val deferreds = mutableListOf<Deferred<Int>>()

        repeat(10) {
            val deferred = async (Dispatchers.Default) {
                var i = 0
                repeat(1000) {
                    i++
                }
                return@async i
            }
            deferreds.add(deferred)
        }

        var result = 0
        deferreds.forEach {
            result += it.await()
        }

        println("i = $result")
    }


// 代码段13
    /**
     * 基于4的优化，函数式编程
     */
    @Test
    fun main5() = runBlocking {
        val result = (1..10).map {
            async (Dispatchers.Default) {
                var i = 0
                repeat(1000) {
                    i++
                }
                return@async i
            }
        }.awaitAll()
            .sum()

        println("i = $result")
    }
}

