package com.wangweijun.myapplication.zhangtao.unite13

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import org.junit.Test
import kotlin.concurrent.thread

class CoroutineDemo {

    @Test

    fun main() {
        val list = getList()
        printList(list)
    }

    fun getList(): List<Int> {
        val list = mutableListOf<Int>()
        println("Add 1")
        list.add(1)
        println("Add 2")
        list.add(2)
        println("Add 3")
        list.add(3)
        println("Add 4")
        list.add(4)

        return list
    }

    fun printList(list: List<Int>) {
        val i = list[0]
        println("Get$i")
        val j = list[1]
        println("Get$j")
        val k = list[2]
        println("Get$k")
        val m = list[3]
        println("Get$m")
    }

/* 运行结果：
Add 1
Add 2
Add 3
Add 4
Get1
Get2
Get3
Get4
*/


    // 看不懂代码没关系，目前咱们只需要关心代码的执行结果
    @Test
    fun main2() {
        println("main2 start ${getThreadInfo()}")
        runBlocking {
            println("runBlocking start ${getThreadInfo()}")
            val sequence = getSequence()
            printSequence(sequence)
            println("runBlocking end ${getThreadInfo()}")
        }
        println("main2 end ${getThreadInfo()}")
    }

    fun getSequence() = sequence {
        println("Add 1 ${getThreadInfo()}")
        yield(1) // 这里必须调用，让步，还同时产生一个值， 协程的代码可以在任意 yield 的地方挂起（Suspend）让出执行权，然后等到合适的时机再恢复（Resume）
        println("Add 2 ${getThreadInfo()}")
        yield(2) // 这里必须调用
        println("Add 3 ${getThreadInfo()}")
        yield(3) // 这里必须调用
        println("Add 4 ${getThreadInfo()}")
        yield(4) // 这里必须调用
    }

    fun printSequence(sequence: Sequence<Int>) {
        val iterator = sequence.iterator()
        val i = iterator.next()
        println("Get$i ${getThreadInfo()}")
        val j = iterator.next()
        println("Get$j ${getThreadInfo()}")
        val k = iterator.next()
        println("Get$k ${getThreadInfo()}")
        val m = iterator.next()
        println("Get$m ${getThreadInfo()}")
    }
    // Thread.currentThread().name 放在携程scope，取到的是携程名字
    fun getThreadInfo() = ", tid = ${Thread.currentThread().id},tname = ${Thread.currentThread().name}"

/*
输出结果：
Add 1
Get1
Add 2
Get2
Add 3
Get3
Add 4
Get4
*/

    // 看不懂代码没关系，目前咱们只需要关心代码的执行结果
    @Test
    fun main3() = runBlocking {
        val channel = getProducer(this)
        testConsumer(channel)
    }

    fun getProducer(scope: CoroutineScope) = scope.produce {
        println("Send:1")
        send(1)
        println("Send:2")
        send(2)
        println("Send:3")
        send(3)
        println("Send:4")
        send(4)
    }

    suspend fun testConsumer(channel: ReceiveChannel<Int>) {
        delay(100)
        val i = channel.receive()
        println("Receive$i")
        delay(100)
        val j = channel.receive()
        println("Receive$j")
        delay(100)
        val k = channel.receive()
        println("Receive$k")
        delay(100)
        val m = channel.receive()
        println("Receive$m")
    }

/*
输出结果：
Send:1
Receive1
Send:2
Receive2
Send:3
Receive3
Send:4
Receive4
*/



    // 代码中一共启动了两个线程
    @Test
    fun main4() {
        println("main4 ${getThreadInfo()}")
        thread {
            println("thread ${getThreadInfo()}")
            Thread.sleep(100)
        }
        Thread.sleep(1000L)
    }

/*
输出结果：
main
Thread-0
*/


    // 代码中在一个线程上一共启动了两个协程,也就是一个进程多个线程，一个线程多个携程，
    // 但是携程不绑定线程，可以在线程间切换
    // 协程可以理解为运行在线程当中的、更加轻量的 Task
    // runBlocking 是阻塞当前线程的
    @Test
    fun main5() {
        println("main5 start ${getThreadInfo()}")
        runBlocking {
            println("runBlocking start ${getThreadInfo()}")

            launch {
                println("launch start delay 100ms ${getThreadInfo()}")
                delay(100L)
                println("launch end ${getThreadInfo()}")
            }
            println("runBlocking sleep 5s ${getThreadInfo()}")
//            Thread.sleep(5000L) // 这里阻塞了上一个携程
            delay(5000L) // 这里不会阻塞上一个携程
            println("runBlocking sleep finished ${getThreadInfo()}")
        }
        println("main5 end ${getThreadInfo()}")
    }

/*
输出结果：
main @coroutine#1
main @coroutine#2

这里要配置特殊的VM参数：-Dkotlinx.coroutines.debug
这样一来，Thread.currentThread().name就能会包含：协程的名字@coroutine#1
*/


    // 仅用作研究，工作中别这么写
    @Test
    fun main6() {
        repeat(1000_000_000) {
            thread {
                Thread.sleep(1000000)
            }
        }

        Thread.sleep(10000L)
    }

/*
输出结果：
Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
    at java.lang.Thread.start0(Native Method)
    at java.lang.Thread.start(Thread.java:717)
    at kotlin.concurrent.ThreadsKt.thread(Thread.kt:42)
    at kotlin.concurrent.ThreadsKt.thread$default(Thread.kt:20)
*/


    // 仅用作研究，工作中别这么写
    @Test
    fun main7() = runBlocking {
        repeat(1000_000_000) {
            launch {
                delay(1000000)
            }
        }

        delay(10000L)
    }

/*
运行结果：
error
*/


    @Test
    fun main8() = runBlocking(Dispatchers.IO) {
        repeat(3) {
            launch {
                repeat(3) {
                    println(Thread.currentThread().name)
                    delay(100)
                }
            }
        }

        delay(5000L)
    }

/*
输出结果：
前面是线程名字，后面是携程名字，
DefaultDispatcher-worker-3 @coroutine#2
DefaultDispatcher-worker-2 @coroutine#3
DefaultDispatcher-worker-4 @coroutine#4
DefaultDispatcher-worker-1 @coroutine#2 // 线程切换了
DefaultDispatcher-worker-4 @coroutine#4
DefaultDispatcher-worker-2 @coroutine#3
DefaultDispatcher-worker-2 @coroutine#2 // 线程切换了
DefaultDispatcher-worker-1 @coroutine#4
DefaultDispatcher-worker-4 @coroutine#3

*/

    // 一个线程顺序执行,在没有携程情况下，是正确的
    @Test
    fun main9() {
        repeat(3) {
            Thread.sleep(1000L)
            println("Print-1:${Thread.currentThread().name}")
        }

        repeat(3) {
            Thread.sleep(900L)
            println("Print-2:${Thread.currentThread().name}")
        }
    }

/*
输出结果：
Print-1:main
Print-1:main
Print-1:main
Print-2:main
Print-2:main
Print-2:main
*/

    // 这里一个线程上运行三个携程，也就是三个任务，但是任务之间不阻塞哦
    @Test
    fun main10() = runBlocking {
        println("Print-0:${Thread.currentThread().name}")
        launch {
            repeat(3) {
                delay(1000L)
                println("Print-1:${Thread.currentThread().name}")
            }
        }

        launch {
            repeat(3) {
                delay(900L)
                println("Print-2:${Thread.currentThread().name}")
            }
        }
        delay(3000L)
    }

/*
输出结果：
Print-2:main @coroutine#3
Print-1:main @coroutine#2
Print-2:main @coroutine#3
Print-1:main @coroutine#2
Print-2:main @coroutine#3
Print-1:main @coroutine#2
*/

    @Test
    fun main11() = runBlocking {
        launch {
            repeat(3) {
                Thread.sleep(1000L) // Thread 阻塞了Print-2语句，阻塞了下个携程,尽量使用 delay，而不是 sleep。
                println("Print-1:${Thread.currentThread().name}")
            }
        }

        launch {
            repeat(3) {
                Thread.sleep(900L)
                println("Print-2:${Thread.currentThread().name}")
            }
        }
        delay(3000L)
    }

/*
输出结果：
Print-1:main @coroutine#2
Print-1:main @coroutine#2
Print-1:main @coroutine#2
Print-2:main @coroutine#3
Print-2:main @coroutine#3
Print-2:main @coroutine#3
*/
}