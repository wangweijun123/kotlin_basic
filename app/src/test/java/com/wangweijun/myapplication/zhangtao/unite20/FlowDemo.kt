package com.wangweijun.myapplication.zhangtao.unite20

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.flow.*
import org.junit.Test
import java.util.concurrent.Executors

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/05 11:19 上午
 * version: 1.0
 * desc   :
 * Flow 数据流是啥意思，
 * 1 一个一个的数据处理，并不是把所有数据都产生完之后才处理 --》 冷
 * 2 必须有终止操作符，才会触发上游的数据产生 ----》 懒
 *
 */
class FlowDemo {

    fun main0() {
    }

// 代码段1
    @Test
    fun main() = runBlocking {
        flow {                  // 上游，发源地
            emit(1)             // 挂起函数
            emit(2)
            emit(3)
            emit(4)
            emit(5)
        }.filter { it > 2 }     // 中转站1
            .map { it * 2 }     // 中转站2
            .take(2)            // 中转站3
            .collect {           // 下游
                println(it)
            }
    }

/*
输出结果：
6
8
*/


// 代码段2
    @Test
    fun main2() = runBlocking {
        // flow 与 list 类似
        flowOf(1, 2, 3, 4, 5).filter { it > 2 }
            .map { it * 2 }
            .take(2)
            .collect { // 遍历flow
                println(it)
            }

        listOf(1, 2, 3, 4, 5).filter { it > 2 }
            .map { it * 2 }
            .take(2)
            .forEach { // 遍历list
                println(it)
            }
    }

/*
输出结果
6
8
6
8
*/


// 代码段3
    @Test
    fun main3() = runBlocking {
        // Flow转List
        flowOf(1, 2, 3, 4, 5)
            .toList()
            .filter { it > 2 }
            .map { it * 2 }
            .take(2)
            .forEach {
                println(it)
            }

        // List转Flow
        listOf(1, 2, 3, 4, 5)
            .asFlow()
            .filter { it > 2 }
            .map { it * 2 }
            .take(2)
            .collect {
                println(it)
            }
    }

/*
输出结果
6
8
6
8
*/


    // 代码段4
    @Test
    fun main4() = runBlocking {
        flowOf(1, 2, 3, 4, 5)
            .filter {
                println("filter: $it")
                it > 2
            }
            .map {
                println("map: $it")
                it * 2
            }
            .take(2)
            .onStart { println("onStart") } // 注意这里
            .collect {
                println("collect: $it")
            }
    }

/*
输出结果
onStart
filter: 1
filter: 2
filter: 3
map: 3
collect: 6
filter: 4
map: 4
collect: 8
*/


    // 代码段5
    @Test
    fun main5() = runBlocking {
        flowOf(1, 2, 3, 4, 5)
            .take(2) // 注意这里
            .filter {
                println("filter: $it")
                it > 2 // 满足条件的在集合中
            }
            .map {
                println("map: $it")
                it * 2
            }
            .onStart { println("onStart") }
            .collect {
                println("collect: $it")
            }
    }
/*
输出结果
onStart
filter: 1
filter: 2
*/


    // 代码段6
    @Test
    fun main6() = runBlocking {
        flowOf(1, 2, 3, 4, 5)
            .onCompletion { println("onCompletion") } // 注意这里
            .filter {
                println("filter: $it")
                it > 2
            }
            .take(2)
            .collect {
                println("collect: $it") // 必须有 流 的终结者
            }
    }

/*
输出结果
filter: 1
filter: 2
filter: 3
collect: 3
filter: 4
collect: 4
onCompletion
*/


    // 代码段7
    @Test
    fun main7() = runBlocking {
        launch {
            flow {
                emit(1)
                emit(2)
                emit(3)
            }.onCompletion { println("onCompletion first: $it") }
                .collect {
                    println("collect: $it")
                    if (it == 2) {
                        cancel()            // 1 控制上游不再发送 3
                        println("cancel")
                    }
                }
        }

        delay(100L)

        flowOf(4, 5, 6)
            .onCompletion { println("onCompletion second: $it") }
            .collect {
                println("collect: $it")
                // 仅用于测试，生产环境不应该这么创建异常
                throw IllegalStateException() // 2
            }
    }

/*
collect: 1
collect: 2
cancel
onCompletion first: JobCancellationException: // 3
collect: 4
onCompletion second: IllegalStateException    // 4
*/


    // 代码段8
    @Test
    fun main8() = runBlocking {
        val flow = flow {
            emit(1)
            emit(2)
            throw IllegalStateException()
            emit(3)
        }

        flow.map { it * 2 }
            .catch { println("catch: $it") } // 注意这里， catch能抓上游的异常
            .collect {
                println(it)
            }
    }
/*
输出结果：
2
4
catch: java.lang.IllegalStateException
*/


    // 代码段9
    @Test
    fun main9() = runBlocking {
        val flow = flow {
            emit(1)
            emit(2)
            emit(3)
        }

        flow.map { it * 2 }
            .catch { println("catch: $it") }
            .filter { it / 0 > 1}  // 故意制造异常, catch 没有抓到下游的异常
            .collect {
                println(it)
            } // 终止操作符
    }

/*
输出结果
Exception in thread "main" ArithmeticException: / by zero
*/


// 代码段10

    @Test
    fun main10() = runBlocking {
        flowOf(4, 5, 6)
            .onCompletion { println("onCompletion second: $it") }
            .collect {
                try {
                    println("collect: $it")
                    throw IllegalStateException()
                } catch (e: Exception) {
                    println("Catch $e")
                }
            }
    }


    // 代码段11
    @Test
    fun main11() = runBlocking {
        val flow = flow {
            logX("Start")
            emit(1)
            logX("Emit: 1")
            emit(2)
            logX("Emit: 2")
            emit(3)
            logX("Emit: 3")
        }

        flow.filter {
            logX("Filter: $it")
            it > 2
        }
        flow.flowOn(Dispatchers.IO)  // 注意这里: 上游全部在其他线程(io线程池)， collect终结者运行在main线程
            .collect {
                logX("Collect $it")
            }
    }


    // 代码段19

    @Test
    fun main19() = runBlocking {
        // 冷数据流(只有调用了终止操作符之后，上游才会开始工作)
        val flow = flow {
            (1..3).forEach {
                println("Before send $it")
                emit(it)
                println("Send $it")
            }
        }

        // 热数据流
        val channel = produce<Int>(capacity = 0) {
            (1..3).forEach {
                println("Before send $it")
                send(it)
                println("Send $it")
            }
        }

        println("end")
    }

    /*
    输出结果：
    end
    Before send 1
    // Flow 当中的代码并未执行
    */

/*
输出结果
================================
Start
Thread:DefaultDispatcher-worker-1 @coroutine#2
================================
================================
Filter: 1
Thread:DefaultDispatcher-worker-1 @coroutine#2
================================
================================
Emit: 1
Thread:DefaultDispatcher-worker-1 @coroutine#2
================================
================================
Filter: 2
Thread:DefaultDispatcher-worker-1 @coroutine#2
================================
================================
Emit: 2
Thread:DefaultDispatcher-worker-1 @coroutine#2
================================
================================
Filter: 3
Thread:DefaultDispatcher-worker-1 @coroutine#2
================================
================================
Emit: 3
Thread:DefaultDispatcher-worker-1 @coroutine#2
================================
================================
Collect 3
Thread:main @coroutine#1
================================

 */


// 代码段21

    @Test
    fun main21() = runBlocking {
        fun loadData() = flow {
            repeat(3){
                delay(100L)
                emit(it)
                logX("emit $it")
            }
        }
        // 请留意 Dispatcher 当中的isDaemon = true
        val mySingleDispatcher = Executors.newSingleThreadExecutor {
            Thread(it, "MySingleThread").apply { isDaemon = true }
        }.asCoroutineDispatcher()
        // 模拟Android、Swing的UI
        val uiScope = CoroutineScope(mySingleDispatcher)

        loadData()
            .map { it * 2 }
            .flowOn(Dispatchers.IO) // 1，耗时任务,指定loaddata运行在io线程池中
            .onEach {
                logX("onEach $it")
            }
            .launchIn(uiScope)      // 2，UI任务, 这也是制定线程上游代码的运行线程，这里是onEach

        delay(1000L)
    }


// 代码段22

    @Test
    fun main22() = runBlocking {
        fun loadData() = flow {
            repeat(3) {
                delay(100L)
                emit(it)
                logX("emit $it")
            }
        }
        fun updateUI(it: Int) { logX(" updateUI it= $it ") }
        fun showLoading() { logX("Show loading") }
        fun hideLoading() { logX("Hide loading") }

        val mySingleDispatcher = Executors.newSingleThreadExecutor {
            Thread(it, "MySingleThread").apply { isDaemon = true }
        }.asCoroutineDispatcher()
        val uiScope = CoroutineScope(mySingleDispatcher)

        loadData()
            .map { it * 2 }
            .flowOn(Dispatchers.IO)
            .catch { throwable ->
                println(throwable)
                hideLoading()                   // 隐藏加载弹窗
                emit(-1)                   // 发生异常以后，指定默认值
            }
            .onEach { updateUI(it) }            // 更新UI界面
            .onStart { showLoading() }          // 显示加载弹窗
            .onCompletion { hideLoading() }     // 隐藏加载弹窗
            .launchIn(uiScope)                  // 指定运行在mySingleDispatcher线程池中

        delay(10000L)
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


}