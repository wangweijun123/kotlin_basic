package com.wangweijun.myapplication.zhangtao.unite22

import com.wangweijun.myapplication.mycoroutines.last.getLastThreadInfo
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.sync.Mutex
import org.junit.Test
import java.util.concurrent.Executors

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/07/03 22:21
 * version: 1.0
 * desc   :
 *
 *  携程同步问题： 你需要确定是否有存在多线程，如果没有，那就不需要
 *  1 synchronized
 *  2 mutex
 *  3 channel
 */
class ContraintSync {


// 代码段1
    // 有并发问题的代码

    @Test
    fun main() = runBlocking {
        var i = 0

        // Default 线程池
        launch(Dispatchers.Default) {
            repeat(1000) {
                i++
            }
        }

        delay(1000L)

        println("i = $i")
    }

    /**
     * 代码中压根就没有并发执行的任务
     */
    @Test
    fun main1() = runBlocking {
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
     * main2 只是在main3上加了log，一直显示10000，但是实际上还是有并发问题，有错觉哦
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
        val lock = Any() // 因为有多线程访问i，是用锁来保证线程安全

        // 重复十次, 肯定产生了十个携程，这十个携程是运行在线程池之上的，所以i处于多线程环境中，有并发问题
        repeat(10){
            val job = launch(Dispatchers.Default) { // 10个协程运行在线程池之上，i处于多线程环境中,所以有并发问题
                repeat(1000) {
                    synchronized(lock) { // 用锁来保证线程安全
//                        prepare() // build error: synchronized(){} 当中调用挂起函数，编译器会给你报错
                        i++
                    }
                }
            }
            jobs.add(job)
        }

        // 等待计算完成
        jobs.joinAll()

        println("i = $i")
    }

    private suspend fun prepare() {
        println("prepare -> ${getLastThreadInfo()}")
        delay(1000L)
        println("prepare end -> ${getLastThreadInfo()}")
    }


    // 代码段6
    /**
     * 10 个协程运行在线程池上(注意这里线程池中只有一个线程，所以并不会有并发问题)i = 10000
     */
    @Test
    fun main33() = runBlocking {
        // CoroutineDispatcher 携程分发，也就是任务分发到哪个线程池之上
        val mySingleDispatcher: ExecutorCoroutineDispatcher = Executors.newSingleThreadExecutor {
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


    // 代码段2
    @Test
    fun mainUseMutex() = runBlocking {
        var i = 0
        val jobs = mutableListOf<Job>()
        val mutex: Mutex = Mutex() // 变化在这里
        // 重复十次
        repeat(10){
            val job = launch(Dispatchers.Default) {
                repeat(1000) {
                    mutex.lock() // 必须加锁后解锁，才能保证线程安全
                    i++
                    mutex.unlock()
                }
            }
            jobs.add(job)
        }

        // 等待计算完成
        jobs.joinAll()

        println("i = $i")
    }
    /*
    输出结果
    i = 10000
    */

    // 代码段10
    @Test
    fun main10() = runBlocking {
        val mutex = Mutex()

        var i = 0
        val jobs = mutableListOf<Job>()

        repeat(10) {
            val job = launch(Dispatchers.Default) {
                repeat(1000) {
                    // 变化在这里
                    mutex.withLock {
                        i++
                    }
                }
            }
            jobs.add(job)
        }
        // Suspends current coroutine until all given jobs are complete.
        jobs.joinAll()

        println("i = $i")
    }

    // withLock的定义
     suspend inline fun <T> Mutex.withLock(owner: Any? = null, action: () -> T): T {
        lock(owner)
        try {
            return action()
        } finally {
            unlock(owner)
        }
    }


    // 代码段11

    sealed class Msg {
        // 消息
        object AddMsg : Msg()

        // 结果
        class ResultMsg(
            val result: CompletableDeferred<Int>
        ) : Msg()
    }
//    object AddMsg : Msg()
//
//    class ResultMsg(
//        val result: CompletableDeferred<Int>
//    ) : Msg()

    @Test
    fun main11() = runBlocking {

        suspend fun addActor() = actor<Msg> {
            var counter = 0
            for (msg in channel) {
                when (msg) {
                    is Msg.AddMsg -> counter++
                    is Msg.ResultMsg -> msg.result.complete(counter)
                }
            }
        }

        val actor = addActor()
        val jobs = mutableListOf<Job>()

        repeat(10) {
            val job = launch(Dispatchers.Default) {
                repeat(1000) {
                    actor.send(Msg.AddMsg)
                }
            }
            jobs.add(job)
        }

        jobs.joinAll()

        val deferred = CompletableDeferred<Int>()
        actor.send(Msg.ResultMsg(deferred))

        val result = deferred.await()
        actor.close()

        println("i = ${result}")
    }


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

