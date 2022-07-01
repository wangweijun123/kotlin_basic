package com.wangweijun.myapplication.zhangtao.unite14

import kotlinx.coroutines.*
import org.junit.Test
import java.lang.Thread.sleep
import kotlin.concurrent.thread

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/03 2:26 下午
 * version: 1.0
 * desc   : 三种启动携程方式
 */
class CoroutineDemo2 {


    /**
     * CoroutineScope.launch(
    context: CoroutineContext = EmptyCoroutineContext, // 协程代码运行在哪个线程
    start: CoroutineStart = CoroutineStart.DEFAULT, // 启动模式：立即执行、懒加载执行
    block: suspend CoroutineScope.() -> Unit // 你写的协程代码
    )
     *
     *
     */
    @Test
    fun main() {
        GlobalScope.launch(Dispatchers.IO) {
            // Coroutine started:DefaultDispatcher-worker-1 @coroutine#1
            println("Coroutine started:${Thread.currentThread().name}")
            delay(1000L)
            println("Hello World!")

        }
        // After launch:Test worker
        println("After launch:${Thread.currentThread().name}")
        Thread.sleep(10000L)
    }

/*
输出结果：
After launch:main
Coroutine started:DefaultDispatcher-worker-1 @coroutine#1
*/


    /* delay 函数的定义: 挂起函数:拥有挂起与恢复能力
         注意这个关键字
              ↓                                            */
    suspend fun delayMe(timeMillis: Long) {
        delay(timeMillis)
    }

    // 仅用于研究，生产环境不建议使用GlobalScope
    @Test
    fun main2() {
        // ①
        val job = GlobalScope.launch {
            // ②
            val start = System.currentTimeMillis()
            delayMe(1000L)
            println("Hello World! spend time=${System.currentTimeMillis() - start}")
        }
        // ③
        Thread.sleep(2000L)
    }

/*
输出结果；
Hello World!
*/


    @Test
    fun main3() {
        // launch 返回不了结果
        // GlobalScope 单利，兄弟S  CoroutineScope
        GlobalScope.launch {
            println("Coroutine started!")

            delay(1000L)
            println("Hello World!") // 主线程退出，携程中的hello world不会打印
        }
        sleep(2000L)
        println("Process end!")
    }
/*
输出结果；
Process end!
*/

    @Test
    fun main4() {
        //              守护线程
        //                 ↓
        thread(isDaemon = true) {
            Thread.sleep(1000L)
            println("Hello World!")
        }
    }

/*
输出结果；
无
*/

    /**
     * 以上的协程代码运行顺序是 1、5、6、2、3、4、7
     * launch 不会阻塞线程的执行
     */
    @Test
    fun main5() {
        // context 指定携程block运行在哪个线程,默认是当前线程,context = Dispatchers.IO
        GlobalScope.launch() {                // 1
            println("Coroutine started!")   // 2
            delay(1000L)                    // 3
            println("Hello World!")         // 4
        }

        println("After launch!")            // 5
        Thread.sleep(2000L)                 // 6
        println("Process end!")             // 7
    }

/*
输出结果：
After launch!
Coroutine started!
Hello World!
Process end!
*/


    // 顺序执行：1、2、3、4、5、6、7。runBlocking 与 launch 的最大差异
    @Test
    fun main55() {
        runBlocking {                       // 1
            // runBlocking阻塞当前线程，也就是携程中的代码与携程外的代码都是运行主线程中
            println("Coroutine started!")   // 2
            delay(1000L)                    // 3
            println("Hello World!")         // 4
        }

        println("After runBlocking!")            // 5
        Thread.sleep(1000L)                 // 6
        println("Process end!")             // 7
    }

/*
输出结果：
Coroutine started!
Hello World!
After launch!
Process end!
*/
    // runBlocking就启动了三个协程
    // runBlocking 阻塞线程执行
    @Test
    fun main555() {
        runBlocking {
            println("First:${Thread.currentThread().name}")
            delay(1000L)
            println("Hello First!")
        }

        runBlocking {
            println("Second:${Thread.currentThread().name}")
            delay(1000L)
            println("Hello Second!")
        }

        runBlocking {
            println("Third:${Thread.currentThread().name}")
            delay(1000L)
            println("Hello Third!")
        }

        // 删掉了 Thread.sleep
        println("Process end!")
    }

/*
输出结果：
First:main @coroutine#1
Hello First!
Second:main @coroutine#2
Hello Second!
Third:main @coroutine#3
Hello Third!
Process end!
*/

    fun func1(num: Int): Double {
        return num.toDouble()
    }

    @Test
    fun main6() {
        // ::func1 函数引用
        val f1: (Int) -> Double = ::func1
        val re = f1(900)
        println("re = $re")


    }


//  这里的代码只能写在顶层文件中，不能写在class中
//    private fun CoroutineScope.func2(num: Int): Double {
//        return num.toDouble()
//    }
    // 这个函数应该是 CoroutineScope 类的成员方法或是扩展方法，并且，它的参数类型必须是 Int，返回值类型必须是 Double
//    val f2: CoroutineScope.(Int) -> Double = CoroutineScope::func2


    suspend fun func3(num: Int): Double {
        delay(100L)
        return num.toDouble()
    }



    @Test
    fun main7() {
        val f3: suspend (Int) -> Double = ::func3
//        f3(2) // build error 因为这是一个suspend，要么在携程中调用，要么被 挂起函数调用
        GlobalScope.launch {
            println("result = ${f3(222)}")
        }
        Thread.sleep(2000)
    }

    @Test
    fun main8() {
        // 接口中写过了 impl里面就不用写
        val result = runBlocking {
            "Coroutine done!"
        }
        // 同步阻塞拿到结果
        println("Result is: $result")
    }

    @Test
    fun main82() {
        val result = runBlocking {
            //可写可不写
//            return@runBlocking "Coroutine done!"
            "Coroutine done!"
        }
        // 同步阻塞拿到结果
        println("Result is: $result")
    }
    /*输出结果：Result is: Coroutine done!*/


    /**
     *
     */
    @Test
    fun main9() = runBlocking {
        // In runBlocking:Test worker @coroutine#1
        println("In runBlocking:${Thread.currentThread().name}")
        // 第一: 只有一个线程，第二：产生了两个携程(任务)
        // deferred 延期的意思
        // async是 CoroutineScope.async 一个成员或者扩展函数，为什么能直接调用，因为
        // runBlocking函数最后一个参数的类型是“suspend CoroutineScope.() -> T”，
        // 因此在lambda中拥有了CoroutineScope对象
        // async 启动了一个异步协程(注意，还是在同一个线程里面)
        val deferred: Deferred<String> = async {
            // In async:Test worker @coroutine#2
            println("In async:${Thread.currentThread().name}")
            delay(1000L) // 模拟耗时操作
            return@async "Task completed!"
        }

        println("After async:${Thread.currentThread().name}")
        // 阻塞当前携程，等待deferred携程执行完成拿到结果
        // 大白话，就是外层携程等待里面的携程执行完成
        // deferred.await() 运行在Test worker @coroutine#1中，@coroutine#1被@coroutine#2阻塞了，只有@coroutine#2
        // 返回，@coroutine#1 才能继续走下去,也就是才能打印Result
        val result = deferred.await()
        println("Result is: $result   tname=${Thread.currentThread().name}")
    }
    /*
    输出结果：
    In runBlocking:main @coroutine#1
    After async:main @coroutine#1 // 注意，它比“In async”先输出
    In async:main @coroutine#2
    Result is: Task completed!
    */


    @Test
    fun main10() = runBlocking {
        val deferred: Deferred<String> = async {
            println("In async:${Thread.currentThread().name}")
            delay(1000L) // 模拟耗时操作
            println("In async after delay!")
            return@async "Task completed!"
        }

        // 不再调用 deferred.await()， 但是async中代码依然会执行
        delay(2000L)
    }

}