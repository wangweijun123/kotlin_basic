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

const val DX_TAG = "wangweijundx"
/**
 * suspend 函数就是里面的耗时代码能切换线程
 * @return String
 */
suspend fun getLoginInfo2(): UserInfo2 {
    Log.d(DX_TAG, " getLoginInfo2 -> ${getLastThreadInfo()}")
    return withContext(Dispatchers.IO) {
        delay(1000)
        // 子线程运行
        Log.d(DX_TAG, "withContext getLoginInfo2 -> ${getLastThreadInfo()}")
//        return@withContext UserInfo2("dx", 18)
        UserInfo2(1, "dx", 18)
    }
}

suspend fun getFrientList2(userId: Int): List<UserInfo2> {
    Log.d(DX_TAG, "getFrientList2 -> ${getLastThreadInfo()}")
    return withContext(Dispatchers.IO) {
        delay(1000)
        // 子线程运行
        Log.d(DX_TAG, "userId = $userId, withContext getFrientList2 -> ${getLastThreadInfo()}")
        listOf(UserInfo2(2, "wwj", 18))
    }
}