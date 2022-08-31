package com.wangweijun.myapplication.zhangtao.unite23

import kotlinx.coroutines.*
import org.junit.Test
import java.util.concurrent.Executors

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/04 9:37 下午
 * version: 1.0
 * desc   :
 */
class TryCatchDemo {
    @Test
    fun cancelLaunch() = runBlocking {
        val job = launch {
            delay(1000L)
            println("job finished")
        }

        job.cancel()    // 调用cancel后，不会输出 job finished
        delay(1500L)
    }

// 代码段1
    @Test
    fun main() = runBlocking {
        val job = launch(Dispatchers.Default) {
            var i = 0
            while (true) {
                Thread.sleep(500L)
                i ++
                println("i = $i")
            }
        }

        delay(2000L)

        job.cancel()
        job.join()

        println("End")
    }

/*
输出结果

i = 1
i = 2
i = 3
i = 4
i = 5
// 永远停不下来
*/


// 代码段2
    @Test
    fun main2() = runBlocking {
        val job = launch(Dispatchers.Default) {
            var i = 0
            // 变化在这里
            while (isActive) {
                Thread.sleep(500L)
                i ++
                println("i = $i")
            }
        }

        delay(2000L)

        job.cancel()
        job.join()

        println("End")
    }

/*
输出结果
i = 1
i = 2
i = 3
i = 4
i = 5
End
*/


// 代码段3

    val fixedDispatcher = Executors.newFixedThreadPool(2) {
        Thread(it, "MyFixedThread").apply { isDaemon = false }
    }.asCoroutineDispatcher()

    @Test
    fun main3() = runBlocking {
        // 父协程
        val parentJob = launch(fixedDispatcher) {

            // 1，注意这里
            launch(Job()) { // 子协程1
                var i = 0
                while (isActive) {
                    Thread.sleep(500L)
                    i ++
                    println("First i = $i")
                }
            }

            launch { // 子协程2
                var i = 0
                while (isActive) {
                    Thread.sleep(500L)
                    i ++
                    println("Second i = $i")
                }
            }
        }

        delay(2000L)

        parentJob.cancel()
        parentJob.join()

        println("End")
        Thread.sleep(10000)
    }

/*
输出结果
First i = 1
Second i = 1
First i = 2
Second i = 2
Second i = 3
First i = 3
First i = 4
Second i = 4
End
First i = 5
First i = 6
// 子协程1永远不会停下来
*/


// 代码段4
    @Test
    fun main4() = runBlocking {
        val parentJob = launch(fixedDispatcher) {

            // 变化在这里
            launch {
                var i = 0
                while (isActive) {
                    Thread.sleep(500L)
                    i ++
                    println("First i = $i")
                }
            }

            launch {
                var i = 0
                while (isActive) {
                    Thread.sleep(500L)
                    i ++
                    println("Second i = $i")
                }
            }
        }

        delay(2000L)

        parentJob.cancel()
        parentJob.join()

        println("End")
    }

/*
输出结果
First i = 1
Second i = 1
First i = 2
Second i = 2
First i = 3
Second i = 3
First i = 4
Second i = 4
End
*/


// 代码段5
    @Test
    fun main5() = runBlocking {

        val parentJob = launch(Dispatchers.Default) {
            launch {
                var i = 0
                while (true) {
                    // 变化在这里
                    delay(500L)
                    i ++
                    println("First i = $i")
                }
            }

            launch {
                var i = 0
                while (true) {
                    // 变化在这里
                    delay(500L)
                    i ++
                    println("Second i = $i")
                }
            }
        }

        delay(2000L)

        parentJob.cancel()
        parentJob.join()

        println("End")
    }

/*
输出结果
First i = 1
Second i = 1
First i = 2
Second i = 2
First i = 3
Second i = 3
End
*/


// 代码段6
    @Test
    fun main6() = runBlocking {

        val parentJob = launch(Dispatchers.Default) {
            launch {
                var i = 0
                while (true) {
                    // 1
                    try {
                        delay(500L)
                    } catch (e: CancellationException) {
                        println("Catch CancellationException")
                        // 2
                        throw e
                    }
                    i ++
                    println("First i = $i")
                }
            }

            launch {
                var i = 0
                while (true) {
                    delay(500L)
                    i ++
                    println("Second i = $i")
                }
            }
        }

        delay(2000L)

        parentJob.cancel()
        parentJob.join()

        println("End")
    }

/*
输出结果
First i = 1
Second i = 1
First i = 2
Second i = 2
First i = 3
Second i = 3
Second i = 4
Catch CancellationException
End
*/


// 代码段8
    @Test
    fun main7() = runBlocking {
        try {
            launch {
                delay(100L)
                1 / 0 // 故意制造异常
            }
        } catch (e: ArithmeticException) {
            println("Catch: $e")
        }

        delay(500L)
        println("End")
    }

/*
输出结果：
崩溃
Exception in thread "main" ArithmeticException: / by zero
*/


// 代码段9
    @Test
    fun main8() = runBlocking {
        var deferred: Deferred<Unit>? = null
        try {
            deferred = async {
                delay(100L)
                1 / 0
            }
        } catch (e: ArithmeticException) {
            println("Catch: $e")
        }

        deferred?.await()

        delay(500L)
        println("End")
    }

/*
输出结果：
崩溃
Exception in thread "main" ArithmeticException: / by zero
*/


// 代码段10
    @Test
    fun main9() = runBlocking {

        launch {
            try {
                delay(100L)
                1 / 0 // 故意制造异常
            } catch (e: ArithmeticException) {
                println("Catch: $e")
            }
        }

        delay(500L)
        println("End")
    }

/*
输出结果：
Catch: java.lang.ArithmeticException: / by zero
End
*/


// 代码段11
    @Test
    fun main10() = runBlocking {
        var deferred: Deferred<Unit>? = null

        deferred = async {
            try {
                delay(100L)
                1 / 0
            } catch (e: ArithmeticException) {
                println("Catch: $e")
            }
        }

        deferred?.await()

        delay(500L)
        println("End")
    }


// 代码段12
    @Test
    fun main11() = runBlocking {
        val deferred = async {
            delay(100L)
            1 / 0
        }

        try {
            deferred.await()
        } catch (e: ArithmeticException) {
            println("Catch: $e")
        }

        delay(500L)
        println("End")
    }

/*
输出结果
Catch: java.lang.ArithmeticException: / by zero
崩溃：
Exception in thread "main" ArithmeticException: / by zero
*/


// 代码段13
    @Test
    fun main12() = runBlocking {
        val deferred = async {
            delay(100L)
            1 / 0
        }

        delay(500L)
        println("End")
    }

/*
输出结果
崩溃：
Exception in thread "main" ArithmeticException: / by zero
*/


// 代码段14
    @Test
    fun main13() = runBlocking {
        val scope = CoroutineScope(SupervisorJob())
        scope.async {
            delay(100L)
            1 / 0
        }

        delay(500L)
        println("End")
    }

/*
输出结果
End
*/


// 代码段15
    @Test
    fun main14() = runBlocking {
        val scope = CoroutineScope(SupervisorJob())
        // 变化在这里
        val deferred = scope.async {
            delay(100L)
            1 / 0
        }

        try {
            deferred.await()
        } catch (e: ArithmeticException) {
            println("Catch: $e")
        }

        delay(500L)
        println("End")
    }

/*
输出结果
Catch: java.lang.ArithmeticException: / by zero
End
*/


// 代码段17
    @Test
    fun main15() = runBlocking {

        val scope = CoroutineScope(coroutineContext)

        scope.launch {
            async {
                delay(100L)
            }

            launch {
                delay(100L)

                launch {
                    delay(100L)
                    1 / 0 // 故意制造异常
                }
            }

            delay(100L)
        }

        delay(1000L)
        println("End")
    }

/*
输出结果
Exception in thread "main" ArithmeticException: / by zero
*/


// 代码段18
    @Test
    fun main16() = runBlocking {
        val myExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            println("Catch exception: $throwable")
        }

        // 注意这里
        val scope = CoroutineScope(coroutineContext + Job() + myExceptionHandler)

        scope.launch {
            async {
                delay(100L)
            }

            launch {
                delay(100L)

                launch {
                    delay(100L)
                    1 / 0 // 故意制造异常
                }
            }

            delay(100L)
        }

        delay(1000L)
        println("End")
    }

/*
Catch exception: ArithmeticException: / by zero
End
*/

    // 代码段19
        @Test
    fun main17() = runBlocking {
        val myExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            println("Catch exception: $throwable")
        }

        // 不再传入myExceptionHandler
        val scope = CoroutineScope(coroutineContext)
        scope.launch {
            async {
                delay(100L)
            }
            launch {
                delay(100L)
                // 变化在这里
                launch(myExceptionHandler) {
                    delay(100L)
                    1 / 0
                }
            }
            delay(100L)
        }
        delay(1000L)
        println("End")
    }
/*
输出结果
崩溃：
Exception in thread "main" ArithmeticException: / by zero
*/
}