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
    withContext(Dispatchers.IO) {
        // 协程挂起: 每一次从主线程切换到io线程
        // 协程恢复: 每一次从io线程切换到主线程
        // 自线程运行
        Log.d("wangweijundx", "withContext Dispatchers.IO -> ${getLastThreadInfo()}")
        delay(5000)
    }
    Log.d("wangweijundx", "getLoginInfo finished -> ${getLastThreadInfo()}")
    return "i am logined"
}

fun getLastThreadInfo() = ", tid = ${Thread.currentThread().id},tname = ${Thread.currentThread().name}"