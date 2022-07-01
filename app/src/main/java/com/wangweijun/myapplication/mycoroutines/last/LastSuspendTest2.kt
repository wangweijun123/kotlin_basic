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
 * suspend 函数就是里面的耗时代码能切换线程
 * @return String
 */
suspend fun getLoginInfo2(): String {
    withContext(Dispatchers.IO) {
        // 自线程运行
        Log.d("wangweijundx", "withContext Dispatchers.IO -> ${getLastThreadInfo()}")
    }
    return "i am logined"
}