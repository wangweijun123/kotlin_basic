package com.wangweijun.myapplication.zhangtao.unite15

import kotlinx.coroutines.*
import org.junit.Test
import kotlin.random.Random
import kotlin.system.measureTimeMillis

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/03 11:35 下午
 * version: 1.0
 * desc   :
 */
class JobDemo {

// 代码段2

    @Test
    fun main() = runBlocking {
        val job = launch {
            delay(1000L)
        }
        job.log()       // ①
        job.cancel()    // ②
        job.log()       // ③
        delay(1500L)
    }

    /**
     * 打印Job的状态信息
     */
    fun Job.log() {
        logX("""
        isActive = $isActive
        isCancelled = $isCancelled
        isCompleted = $isCompleted
    """.trimIndent())
    }

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


/*
输出结果：
================================
isActive = true
isCancelled = false
isCompleted = false
Thread:main @coroutine#1
================================
================================
isActive = false
isCancelled = true
isCompleted = false
Thread:main @coroutine#1
================================
*/


// 代码段3
    @Test
    fun main2() = runBlocking {
        //                  变化在这里
        //                      ↓
        val job = launch(start = CoroutineStart.LAZY) {
            logX("Coroutine start!")
            delay(1000L)
        }
        delay(500L)
        job.log()
        job.start()     // 变化在这里
        job.log()
        delay(500L)
        job.cancel()
        delay(500L)
        job.log()
        delay(2000L)
        logX("Process end!")
    }

/*
输出结果：
================================
isActive = false
isCancelled = false
isCompleted = false
Thread:main @coroutine#1
================================
================================
isActive = true
isCancelled = false
isCompleted = false
Thread:main @coroutine#1
================================
================================
Coroutine start!
Thread:main @coroutine#2
================================
================================
isActive = false
isCancelled = true
isCompleted = true
Thread:main @coroutine#1
================================
================================
Process end!
Thread:main @coroutine#1
================================
*/


// 代码段4
    @Test
    fun main3() = runBlocking {
        val job = launch(start = CoroutineStart.LAZY) {
            logX("Coroutine start!")
            delay(1000L)
        }
        delay(500L)
        job.log()
        job.start()
        job.log()
        delay(1100L)    // ①
        job.log()
        delay(2000L)    // ②
        logX("Process end!")
    }



// 代码段6

    @Test
    fun main4() = runBlocking {
        val job = launch(start = CoroutineStart.LAZY) {
            logX("Coroutine start!")
            download()
            logX("Coroutine end!")
        }
        delay(500L)
        job.log()
        job.start()
        job.log()
        job.invokeOnCompletion {
            job.log() // 协程结束以后就会调用这里的代码
        }
        job.join()      // 等待协程执行完毕
        logX("Process end!")
    }

    suspend fun download() {
        // 模拟下载任务
        val time = (Random.nextDouble() * 1000).toLong()
        logX("Delay time: = $time")
        delay(time)
    }

/*
运行结果：
================================
isActive = false
isCancelled = false
isCompleted = false
Thread:main @coroutine#1
================================
================================
isActive = true
isCancelled = false
isCompleted = false
Thread:main @coroutine#1
================================
================================
Coroutine start!
Thread:main @coroutine#2
================================
================================
Delay time: = 252
Thread:main @coroutine#2
================================
================================
Coroutine end!
Thread:main @coroutine#2
================================
================================
isActive = false
isCancelled = false
isCompleted = true
Thread:main @coroutine#2
================================
================================
Process end!
Thread:main @coroutine#1
================================
*/


// 代码段8

    @Test
    fun main5() = runBlocking {
        val deferred = async {
            logX("Coroutine start!")
            delay(1000L)
            logX("Coroutine end!")
            "Coroutine result!"
        }
        val result = deferred.await()
        println("Result = $result")
        logX("Process end!")
    }

/*
输出结果：
================================
Coroutine start!
Thread:main @coroutine#2
================================
================================
Coroutine end!
Thread:main @coroutine#2
================================
Result = Coroutine result!
================================
Process end!
Thread:main @coroutine#1
================================
*/


// 代码段10
    @Test
    fun main6() {
    runBlocking {
            val parentJob: Job
            var job1: Job? = null
            var job2: Job? = null
            var job3: Job? = null

            parentJob = launch {
                // 三个携程是并发等待，所以不是累加,join执行之后，大约等待 4.5秒
                job1 = launch {
                    logX("job1 start ")
                    delay(3000L)
                }

                job2 = launch {
                    logX("job2 start ")
                    delay(4000L)
                }

                job3 = launch {
                    logX("job3 start ")
                    delay(5000L)
                }
            }

            delay(500L)

            parentJob.children.forEachIndexed { index, job ->
                when (index) {
                    0 -> println("job1 === job is ${job1 === job}")
                    1 -> println("job2 === job is ${job2 === job}")
                    2 -> println("job3 === job is ${job3 === job}")
                }
            }
        val start = System.currentTimeMillis()
            parentJob.join() // 这里会挂起大约5秒钟,大约4500ms
            logX("Process end! ${System.currentTimeMillis()-start}")
        }
    }

/*
输出结果：
job1 === job is true
job2 === job is true
job3 === job is true
// 等待大约5秒钟
================================
Process end!
Thread:main @coroutine#1
================================
*/


// 代码段12
    @Test
    fun main7() = runBlocking {
        val parentJob: Job
        var job1: Job? = null
        var job2: Job? = null
        var job3: Job? = null

        parentJob = launch {
            job1 = launch {
                logX("Job1 start!")
                delay(1000L)
                logX("Job1 done!") // ①，不会执行
            }

            job2 = launch {
                logX("Job2 start!")
                delay(3000L)
                logX("Job2 done!") // ②，不会执行
            }

            job3 = launch {
                logX("Job3 start!")
                delay(5000L)
                logX("Job3 done!")// ③，不会执行
            }
        }

        delay(500L)

        parentJob.children.forEachIndexed { index, job ->
            when (index) {
                0 -> println("job1 === job is ${job1 === job}")
                1 -> println("job2 === job is ${job2 === job}")
                2 -> println("job3 === job is ${job3 === job}")
            }
        }

        parentJob.cancel() // 变化在这里
        logX("Process end!")
    }

/*
输出结果：
================================
Job1 start!
Thread:main @coroutine#3
================================
================================
Job2 start!
Thread:main @coroutine#4
================================
================================
Job3 start!
Thread:main @coroutine#5
================================
job1 === job is true
job2 === job is true
job3 === job is true
================================
// 这里不会等待5秒钟
Process end!
Thread:main @coroutine#1
================================
*/


// 代码段13
    @Test
    fun main8() = runBlocking {
        val results = mutableListOf<String>()
        val time = measureTimeMillis {
            results.add(getResult1())
            results.add(getResult2())
            results.add(getResult3())
        }
        println("Time: $time") // Time: 3013, 三个任务只开启了一个携程并且在同一个线程上，所以累加
        println(results)
    }

/*
输出结果：
Time: 3018
[Result1, Result2, Result3]
*/


// 代码段14
    /**
     * 很多异步任务之间都是没有互相依赖的，这样的代码结合挂起函数后
     * 再通过 async 并发来执行，是可以大大提升代码运行效率的
     *
     * 1 协程是有生命周期的 === job, 1 job 自然有生命周期，也就是任务的状态，还是源码: start,cancel,finished,active
     * 2 协程其实是结构化 === job任务之间是有依赖的(父子关系,一个父亲有多个儿子)，像阿里的开源项目...
     */
    @Test
    fun main9() = runBlocking {
        val results: List<String>
        val time = measureTimeMillis {
            val result1 = async { getResult1() } // async 产生了新的协程，但是只有一个线程
            val result2 = async { getResult2() }
            val result3 = async { getResult3() }
            results = listOf(result1.await(), result2.await(), result3.await())
        }
        // 一个线程内并发三个协程
        println("Time: $time") // Time: 1030，这里是并发，开启了三个协程，尽管在一个线程上，三个协程是并发的
        println(results)
    }
    /*
 输出结果：
 Time: 1032
 [Result1, Result2, Result3]
 */

    suspend fun getResult1(): String {
        logX("getResult1()")
        delay(1000L) // 模拟耗时操作
        return "Result1"
    }

    suspend fun getResult2(): String {
        logX("getResult2()")
        delay(1000L) // 模拟耗时操作
        return "Result2"
    }

    suspend fun getResult3(): String {
        logX("getResult3()")
        delay(1000L) // 模拟耗时操作
        return "Result3"
    }
    // B依赖A，与C并发
    @Test
    fun main99() = runBlocking {
        val results: List<String>
        val time = measureTimeMillis {
            val resultB = async {
                // taskB依赖taskA，所以窜行执行
                val temp = taskA()
                taskB(temp)
            }
            val resultC = async { taskC() }
            results = listOf(resultB.await(), resultC.await())
        }
        println("Time: $time") //
        println(results)
    }
    // AB可以同时请求，C依赖AB的结果
    @Test
    fun main999() = runBlocking {
        val results: List<String>
        val time = measureTimeMillis {
            val resultA = async {taskA()}
            val resultB = async { taskB("xxx") }
            results = listOf(resultB.await(), resultB.await())
            taskCC(resultA.await(), resultB.await())
        }
        println("Time: $time") // Time: 5050
        println(results)
    }

//     fun taskA(): String{
//        logX("taskA()")
//        return "resultA"
//    }
//     fun taskB(resultA: String): String{
//        logX("taskB()")
//
//        return "resultB"
//    }
//    fun taskC(): String {
//        logX("taskC()")
//
//        return "resultC"
//    }

//    注意这里是挂起函数
    suspend fun taskA(): String{
        logX("taskA()")
        delay(1000)
        return "resultA"
    }
    suspend fun taskB(resultA: String): String{
        logX("taskB()")
        delay(2000)
        return "resultB"
    }
    suspend fun taskC(): String {
        logX("taskC()")
        delay(3000)
        return "resultC"
    }
    suspend fun taskCC(taskA: String, taskB: String): String {
        logX("taskCC()")
        delay(3000)
        return "resultCC"
    }




// 代码段15
    @Test
    fun main10() = runBlocking {
        val job:Job = launch {
            logX("First coroutine start!")
            delay(1000L)
            logX("First coroutine end!")
        }

        job.join()
        val job2 = launch(job) {
            logX("Second coroutine start!")
            delay(1000L)
            logX("Second coroutine end!")
        }
        job2.join()
        logX("Process end!")
    }
}