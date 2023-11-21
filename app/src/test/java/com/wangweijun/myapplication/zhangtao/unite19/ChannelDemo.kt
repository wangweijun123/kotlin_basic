package com.wangweijun.myapplication.zhangtao.unite19

import kotlinx.coroutines.channels.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/04 11:13 下午
 * version: 1.0
 * desc   : channel 是管道，管道有什么： 容量，超过容量怎么办, 策略, 像生产者与消费者
 */
class ChannelDemo {
    /**
     * 控制台输出带协程信息的log
     */


// 代码段1
    @Test
    fun main() = runBlocking {
        // 1，创建管道 (capacity 容量至关重要如果是 Channel.UNLIMITED，需要等全部发送完成哈)
        val channel = Channel<Int>()

        // 两个携程共享一个通道，一个携程发送消息，另外一个携程接收消息
        launch {
            // 2，在一个单独的协程@coroutine#2, tid:13当中发送管道消息
            (1..3).forEach {
                logX("Send: $it")
                channel.send(it) // 挂起函数
            }
        }

        launch {
            // 3，在一个单独的协程@coroutine#3, tid:13当中接收管道消息
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
        // 单线程实现了生产者与消费者模型，在java中是无法实现的，在java中需要两个线程
        // 需要注意channel中容量，默认capacity=0，就是说没有buffer，当发送一个int，就会挂起，直到接收者接收到
        // 如果是无限容量,只有当全部发送完成后，receiver才会接收到
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
        val channel = Channel<Int>(capacity = Channel.UNLIMITED) {
            println("onUndeliveredElement = $it")
        }

        // 等价这种写法
//    val channel2 = Channel<Int>(Channel.UNLIMITED, onUndeliveredElement = { println("onUndeliveredElement = $it") })

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

// 代码段9

    @Test
    fun main8() = runBlocking {
        // 变化在这里
        val channel: ReceiveChannel<Int> = produce {
            (1..3).forEach {
                send(it)
                logX("Send: $it")
            }
        }

        launch {
            // 3，接收数据
            for (i in channel) {
                logX("Receive: $i")
            }
        }

        logX("end")
    }



    @Test
    fun main9() = runBlocking {
        val model = ChannelModel()
        launch {
            logX(" init ... ")
            model.init()
        }
        logX(" consumeEach xxx ... ")
        model.channel.consumeEach {
            logX("consume it = $it")
        }
    }

// 代码段19

    class ChannelModel {
        // 对外只提供读取功能
        val channel: ReceiveChannel<Int> by ::_channel
        private val _channel: Channel<Int> = Channel()

        suspend fun init() {
            (1..3).forEach {
                logX("send it = $it")
                _channel.send(it)
                delay(1000)
            }
        }
    }


    @Test
    fun main10() = runBlocking {
        // 无限容量的管道
        val channel = Channel<Int>(Channel.UNLIMITED) {
            println("onUndeliveredElement = $it")
        }

        // 等价这种写法
//    val channel2 = Channel<Int>(Channel.UNLIMITED, onUndeliveredElement = { println("onUndeliveredElement = $it") })

        // 放入三个数据
        (1..3).forEach {
            channel.send(it)
        }

        // 取出一个，剩下两个
        channel.receive()

        // 取消当前channel
        println(channel.isClosedForReceive)
        println(channel.isClosedForSend)
        channel.cancel()
        println(channel.isClosedForReceive)
        println(channel.isClosedForSend)
        channel.send(4)
    }
}

fun logX(any: Any?) {
    println("""
================================
$any
Thread name:${Thread.currentThread().name}, tid:${Thread.currentThread().id}
================================""".trimIndent())
}