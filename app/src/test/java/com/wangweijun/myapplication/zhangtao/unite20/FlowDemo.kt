package com.wangweijun.myapplication.zhangtao.unite20

import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/05 11:19 上午
 * version: 1.0
 * desc   :
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
            .collect{           // 下游
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
        flowOf(1, 2, 3, 4, 5).filter { it > 2 }
            .map { it * 2 }
            .take(2)
            .collect {
                println(it)
            }

        listOf(1, 2, 3, 4, 5).filter { it > 2 }
            .map { it * 2 }
            .take(2)
            .forEach {
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
                println("collect: $it")
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
                        cancel()            // 1
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
}