package com.wangweijun.myapplication.zhangtao.unite19

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/04 11:13 下午
 * version: 1.0
 * desc   :
 */
class ChannelDemo {
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

// 代码段1
    @Test
    fun main() = runBlocking {
        // 1，创建管道
        val channel = Channel<Int>()

        launch {
            // 2，在一个单独的协程当中发送管道消息
            (1..3).forEach {
                channel.send(it) // 挂起函数
                logX("Send: $it")
            }
        }

        launch {
            // 3，在一个单独的协程当中接收管道消息
            for (i in channel) {  // 挂起函数
                logX("Receive: $i")
            }
        }

        logX("end")
    }

/*
================================
end
Thread:main @coroutine#1
================================
================================
Receive: 1
Thread:main @coroutine#3
================================
================================
Send: 1
Thread:main @coroutine#2
================================
================================
Send: 2
Thread:main @coroutine#2
================================
================================
Receive: 2
Thread:main @coroutine#3
================================
================================
Receive: 3
Thread:main @coroutine#3
================================
================================
Send: 3
Thread:main @coroutine#2
================================
// 4，程序不会退出
*/


// 代码段2
    @Test
    fun main2() = runBlocking {
        val channel = Channel<Int>()

        launch {
            (1..3).forEach {
                channel.send(it)
                logX("Send: $it")
            }

            channel.close() // 变化在这里
        }

        launch {
            for (i in channel) {
                logX("Receive: $i")
            }
        }

        logX("end")
    }


// 代码段4
    @Test
    fun main3() = runBlocking {
        // 变化在这里
        val channel = Channel<Int>(capacity = Channel.Factory.UNLIMITED)
        launch {
            (1..3).forEach {
                channel.send(it)
                println("Send: $it")
            }
            channel.close() // 变化在这里
        }
        launch {
            for (i in channel) {
                println("Receive: $i")
            }
        }
        println("end")
    }

/*
输出结果：
end
Send: 1
Send: 2
Send: 3
Receive: 1
Receive: 2
Receive: 3
*/


// 代码段5
    @Test
    fun main4() = runBlocking {
        // 变化在这里
        val channel = Channel<Int>(capacity = Channel.Factory.CONFLATED)

        launch {
            (1..3).forEach {
                channel.send(it)
                println("Send: $it")
            }

            channel.close()
        }

        launch {
            for (i in channel) {
                println("Receive: $i")
            }
        }

        println("end")
    }

/*
输出结果：
end
Send: 1
Send: 2
Send: 3
Receive: 3
*/


// 代码段7
    @Test
    fun main5() = runBlocking {
        // 变化在这里
        val channel = Channel<Int>(
            capacity = 3,
            onBufferOverflow = BufferOverflow.DROP_LATEST //
        )

        launch {
            (1..3).forEach {
                channel.send(it)
                println("Send: $it")
            }

            channel.send(4) // 被丢弃
            println("Send: 4")
            channel.send(5) // 被丢弃
            println("Send: 5")

            channel.close()
        }

        launch {
            for (i in channel) {
                println("Receive: $i")
            }
        }

        println("end")
    }

/*
输出结果：
end
Send: 1
Send: 2
Send: 3
Send: 4
Send: 5
Receive: 1
Receive: 2
Receive: 3
*/


// 代码段8
    @Test
    fun main6() = runBlocking {
        // 无限容量的管道
        val channel = Channel<Int>(Channel.UNLIMITED) {
            println("onUndeliveredElement = $it")
        }

        // 等价这种写法
//    val channel = Channel<Int>(Channel.UNLIMITED, onUndeliveredElement = { println("onUndeliveredElement = $it") })

        // 放入三个数据
        (1..3).forEach {
            channel.send(it)
        }

        // 取出一个，剩下两个
        channel.receive()

        // 取消当前channel
        channel.cancel()
    }

/*
输出结果：
onUndeliveredElement = 2
onUndeliveredElement = 3
*/


// 代码段15
    @Test
    fun main7() = runBlocking {
        // 只发送不接受
        val channel = produce<Int>(capacity = 10) {
            (1..3).forEach {
                send(it)
                println("Send $it")
            }
        }

        println("end")
    }

/*
输出结果：
end
Send 1
Send 2
Send 3
程序结束
*/
}