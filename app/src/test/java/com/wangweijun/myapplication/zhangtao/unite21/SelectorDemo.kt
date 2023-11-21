package com.wangweijun.myapplication.zhangtao.unite21

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select
import org.junit.Test

class SelectorDemo {

    suspend fun getCacheInfo(productId: String): Product? {
        delay(100L)
        return Product(productId, 9.9)
    }

    private suspend fun getNetworkInfo(productId: String): Product? {
        delay(200L)
        return Product(productId, 9.8)
    }

    fun updateUI(product: Product) {
        println("${product.productId}==${product.price}")
    }

    // 代码段1
    @Test
    fun main() = runBlocking {


        val startTime = System.currentTimeMillis()

        val productId = "xxxId"
        // 查询缓存
        val cacheInfo = getCacheInfo(productId)
        if (cacheInfo != null) {
            updateUI(cacheInfo)
            println("Time cost: ${System.currentTimeMillis() - startTime}")
        }

        // 查询网络
        val latestInfo = getNetworkInfo(productId)
        if (latestInfo != null) {
            updateUI(latestInfo)
            println("Time cost: ${System.currentTimeMillis() - startTime}")
        }
    }

    data class Product(
        val productId: String,
        val price: Double,

        // 是不是缓存信息
        val isCache: Boolean = false
    )

    /*
    输出结果
    xxxId==9.9
    Time cost: 112
    xxxId==9.8
    Time cost: 314
    */



    // 代码段2
    @Test
    fun main2() = runBlocking {
        val startTime = System.currentTimeMillis()
        val productId = "xxxId"
        //          1，注意这里
        //               ↓
        val product = select<Product?> {
            // 这两个异步任务都会开始执行，谁先返回就是用谁的结果
            // 2，注意这里
            async { getCacheInfo(productId) }
                .onAwait { // 3，注意这里
                    it
                }
            // 4，注意这里
            async { getNetworkInfo(productId) }
                .onAwait {  // 5，注意这里
                    it
                }
        }

        if (product != null) {
            updateUI(product)
            println("Time cost: ${System.currentTimeMillis() - startTime}")
        }
    }

    /*
    输出结果
    xxxId==9.9
    Time cost: 127
    */

    // 代码段5
    @Test
    fun main3() = runBlocking {
        val startTime = System.currentTimeMillis()
        val productId = "xxxId"

        // 1，缓存和网络，并发执行
        val cacheDeferred = async { getCacheInfo(productId) }
        val latestDeferred = async { getNetworkInfo(productId) }

        // 2，在缓存和网络中间，选择最快的结果
        val product = select<Product?> {
            cacheDeferred.onAwait {
                it?.copy(isCache = true)
            }

            latestDeferred.onAwait {
                it?.copy(isCache = false)
            }
        }

        // 3，更新UI
        if (product != null) {
            updateUI(product)
            println("Time cost: ${System.currentTimeMillis() - startTime}")
        }

        // 4，如果当前结果是缓存，那么再取最新的网络服务结果
        if (product != null && product.isCache) {
            println("这是缓存结果，需要等待network结果回来再刷新")
            val latest = latestDeferred.await()?: return@runBlocking
            updateUI(latest)
            println("Time cost: ${System.currentTimeMillis() - startTime}")
        } else {
            println("这是network结果")
        }
    }

    /*
    输出结果：
    xxxId==9.9
    Time cost: 120
    xxxId==9.8
    Time cost: 220
    */


    // 代码段8
    @Test
    fun main4() = runBlocking {
        val startTime = System.currentTimeMillis()
        val channel1 = produce {
            println("send 1")
            send(1)
            delay(200L)
            println("send 2")
            send(2)
            delay(200L)
            send(3)
            delay(150L)
        }

        val channel2 = produce {
            delay(100L)
            println("send a")
            send("a")
            delay(200L)
            println("send b")
            send("b")
            delay(200L)
            send("c")
        }

        channel1.consumeEach {
            println(it)
        }

        channel2.consumeEach {
            println(it)
        }

        println("Time cost: ${System.currentTimeMillis() - startTime}")
    }

    /*
    输出结果
    1
    2
    3
    a
    b
    c
    Time cost: 989
    */

    // 代码段9
    @Test
    fun main5() = runBlocking {
        val startTime = System.currentTimeMillis()
        val channel1 = produce {
            send("1")
            delay(200L)
            send("2")
            delay(200L)
            send("3")
            delay(150L)
        }

        val channel2 = produce {
            delay(100L)
            send("a")
            delay(200L)
            send("b")
            delay(200L)
            send("c")
        }

        suspend fun selectChannel(channel1: ReceiveChannel<String>, channel2: ReceiveChannel<String>): String = select<String> {
            // 1， 选择channel1
            channel1.onReceive{
                it.also { println(it) }
            }
            // 2， 选择channel1
            channel2.onReceive{
                it.also { println(it) }
            }
        }

        // 3， 选择6次结果, 3次channel1, 3次channel2
        repeat(6){// 3， 选择6次结果
            selectChannel(channel1, channel2)
        }

        println("Time cost: ${System.currentTimeMillis() - startTime}")
    }

    /*
    输出结果
    1
    a
    2
    b
    3
    c
    Time cost: 540
    */

    // 代码段13

    @Test
    fun main6() = runBlocking {
        val startTime = System.currentTimeMillis()
        val channel1 = produce<String> {
            delay(15000L)
        }

        val channel2 = produce {
            delay(100L)
            send("a")
            delay(200L)
            send("b")
            delay(200L)
            send("c")
        }

        suspend fun selectChannel(channel1: ReceiveChannel<String>, channel2: ReceiveChannel<String>): String =
            select<String> {
                channel1.onReceiveCatching {
                    it.getOrNull() ?: "channel1 is closed!"
                }
                channel2.onReceiveCatching {
                    it.getOrNull() ?: "channel2 is closed!"
                }
            }

        repeat(6) {
            val result = selectChannel(channel1, channel2)
            println(result)
        }

        // 变化在这里
        channel1.cancel()
        channel2.cancel()

        println("Time cost: ${System.currentTimeMillis() - startTime}")
    }


    @Test
    fun main7() = runBlocking {
        suspend fun <T> fastest(vararg deferreds: Deferred<T>): T = select {
            fun cancelAll() {
                deferreds.forEach { it.cancel() }
            }

            println("deferreds size: ${deferreds.size}")
            for (deferred in deferreds) {
                println("监听每个async的结果")
                deferred.onAwait {
                    cancelAll()
                    it
                }
            }
        }

        val deferred1 = async {
            delay(100L)
            println("done1")    // 没机会执行
            "result1"
        }

        val deferred2 = async {
            delay(50L)
            println("done2")
            "result2"
        }

        val deferred3 = async {
            delay(10000L)
            println("done3")    // 没机会执行
            "result3"
        }

        val deferred4 = async {
            delay(2000L)
            println("done4")    // 没机会执行
            "result4"
        }

        val deferred5 = async {
            delay(14000L)
            println("done5")    // 没机会执行
            "result5"
        }

        val result = fastest(deferred1, deferred2, deferred3, deferred4, deferred5)
        println(result)
    }

    /*
    输出结果
    done2
    result2
    */
}