package com.wangweijun.myapplication.mycoroutines.last

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/06/19 12:50
 * version: 1.0
 * desc   :
 */


/**
 * suspend 函数就是里面的耗时代码能切换线程，挂起不阻塞
 * suspend 挂起函数只能在协程中使用
 * @return String
 */
suspend fun getLoginInfo(): String {
    Log.d("wangweijundx", "getLoginInfo -> ${getLastThreadInfo()}")
    // withContext 是一个挂起函数, 虽然指定io线程运行，但是后面的代码等待(线程不一样哦，这就是挂起的魅力:异步代码同步写)
    withContext(Dispatchers.IO) {
        // 协程挂起: 每一次从主线程切换到io线程
        // 协程恢复: 每一次从io线程切换到主线程
        // io线程运行
        Log.d("wangweijundx", "withContext Dispatchers.IO -> ${getLastThreadInfo()}")
        delay(10000)
    }
    // 如下代码 main 线程运行，并且运行在 withContext block 之后
    Log.d("wangweijundx", "getLoginInfo finished -> ${getLastThreadInfo()}")
    return "i am logined"
}

suspend fun getLoginInfoNotDispatcher(): String {
    // 测试不会 main thread调用过来不会anr，但是最好是使用withContext指定线程 delay
    Log.d("wangweijundx", "delay 10000 -> ${getLastThreadInfo()}")
    delay(10000)
    Log.d("wangweijundx", "getLoginInfo finished -> ${getLastThreadInfo()}")
    return "i am logined"
}


fun getLoginInfoNotSuspend(): String {
    Log.d("wangweijundx", "Thread sleep 10000 -> ${getLastThreadInfo()}")
    Thread.sleep(10000)
    Log.d("wangweijundx", "getLoginInfo finished -> ${getLastThreadInfo()}")
    return "i am logined"
}

fun getLastThreadInfo() = ", tid = ${Thread.currentThread().id},tname = ${Thread.currentThread().name}"