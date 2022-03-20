package com.wangweijun.myapplication.myjava

import io.reactivex.Observable
import org.junit.Test
import java.lang.RuntimeException
import java.util.concurrent.TimeUnit

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/03/12 2:10 上午
 * version: 1.0
 * desc   :
 */
class MainTimerKotlin {

    @Test
    fun testFunctionThrottler3() {
        var finished = false
        // 请求次数
        var asyncReqCount = 3
        // 间隔时间
        val asyncReqSeconds = 1L
        // 随时取消
        Observable.interval(0, asyncReqSeconds, TimeUnit.SECONDS)
            .takeUntil {
                println("${System.currentTimeMillis()} takeUntil asyncReqCount = $asyncReqCount, success=$finished, tid = ${Thread.currentThread().id}, ")
                asyncReqCount == 0 || finished
            }
            .subscribe({
                asyncReqCount--
                println("${System.currentTimeMillis()} 第 it = $asyncReqCount 次干活, tid = ${Thread.currentThread().id}, ${System.currentTimeMillis()}")
                // 干活
                if (asyncReqCount == 1) {
                        finished = true
                        println("成功了")

//                    println("异常了")
//                    throw RuntimeException("xxxx") // 异常后，后面的次数终止执行，直接到onerror接口

                }
            }, {
                println("${System.currentTimeMillis()} onError it = $it, tid = ${Thread.currentThread().id}, ${System.currentTimeMillis()}")
            })

        println("sleep tid = ${Thread.currentThread().id}")
        Thread.sleep(5000)
        println("finished .., tid = ${Thread.currentThread().id}")

    }


    @Test
    fun testFunctionThrottler() {
        val result = getMypByThree()
        println("testFunctionThrottler, tid = ${Thread.currentThread().id} result = " + result)
        Thread.sleep(5000)
        println("finished testFunctionThrottler.., tid = ${Thread.currentThread().id}")
    }

    fun getMypByThree() : Observable<MyP>? {
        var finished = false
        // 请求次数
        var asyncReqCount = 3
        // 间隔时间
        val asyncReqSeconds = 1000L
        var ob: Observable<MyP>? = null
        // 随时取消
        for (i in 1..asyncReqCount) {
            print("$i ")
            ob = getMyp()
            Thread.sleep(asyncReqSeconds)
        }
        // 这个线程跑完了
        println("getMypByThree ob = ${ob} , tid = ${Thread.currentThread().id}" )
        return ob
    }

    fun getMyp(): Observable<MyP> {
        return Observable.create<MyP> {
            println("create Observable , tid = ${Thread.currentThread().id}")
        }
    }

    fun getMypByThree2() : Observable<MyP>? {
        var finished = false
        // 请求次数
        var asyncReqCount = 3
        // 间隔时间
        val asyncReqSeconds = 1L
        // 随时取消
        var ob: Observable<MyP>? = null
        Observable.interval(0, asyncReqSeconds, TimeUnit.SECONDS)
            .takeUntil {
                println("${System.currentTimeMillis()} takeUntil asyncReqCount = $asyncReqCount, success=$finished, tid = ${Thread.currentThread().id}, ")
                asyncReqCount == 0 || finished
            }
            .subscribe({
                asyncReqCount--
                println("${System.currentTimeMillis()} 第 it = $asyncReqCount 次干活, tid = ${Thread.currentThread().id}, ${System.currentTimeMillis()}")
                // 干活
                ob = getMyp()
                println("getMyp ob = ${ob} , tid = ${Thread.currentThread().id}")
//                if (asyncReqCount == 1) {
//                    finished = true
//                    println("成功了")
//
//                }
            }, {
                println("${System.currentTimeMillis()} onError it = $it, tid = ${Thread.currentThread().id}, ${System.currentTimeMillis()}")
            })

        // 这个线程跑完了
        println("getMypByThree ob = ${ob} , tid = ${Thread.currentThread().id}" )
        return ob
    }

    data class MyP(val id: String)
}