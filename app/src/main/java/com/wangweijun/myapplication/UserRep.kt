package com.wangweijun.myapplication

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

object UserRep {

    suspend fun getUserInfo(): String {
        // getUserInfo..., tid = 13,tname = Test worker @coroutine#1
        println("getUserInfo...${getThreadInfo()}")
        // 切换线程::: 指定这块耗时代码运行在线程池 Dispatchers.IO,因为他耗时
        // 如果不定义成suspend函数，你是切换不了线程的
        // withContext 是suspend函数，所以挂起函数后面的代码，会被打包成callback
        // 1, withContext 在io线程池运行，2, io线程池运行完，callback回到主线程
        val result = withContext(Dispatchers.IO) {
            // 指定这块代码运行在Dispatchers.IO tid = 16,tname = DefaultDispatcher-worker-1 @coroutine#1
            println("getUserInfo...withContext start...${getThreadInfo()}")
            delay(1000L)
            println("getUserInfo...withContext end...${getThreadInfo()}")
            return@withContext "BoyCoder"
        }
        // 这句话只有当withContext{} 运行完才运行
        // getUserInfo...return ..., tid = 13,tname = Test worker @coroutine#1
        println("getUserInfo...return ...${getThreadInfo()}")
        return result
    }

    suspend fun getFriendList(user: String): String {
        println("getFriendList...${getThreadInfo()}")

        // 控制协程执行的线程池
        val result = withContext(Dispatchers.IO) {
            println("getFriendList...withContext start...${getThreadInfo()}")
            delay(1000L)
            println("getFriendList...withContext end...${getThreadInfo()}")
            return@withContext "Tom, Jack"
        }
        println("getFriendList...return ...${getThreadInfo()}")
        return result
    }

    //挂起函数
// ↓
    suspend fun getFeedList(list: String): String {
        println("getFeedList...${getThreadInfo()}")
        withContext(Dispatchers.IO) {
            println("getFeedList...withContext start...${getThreadInfo()}")
            delay(1000L)
            println("getFeedList...withContext start...${getThreadInfo()}")
        }
        println("getFeedList...return ...${getThreadInfo()}")
        return "{FeedList..}"
    }


    fun getThreadInfo() = ", tid = ${Thread.currentThread().id},tname = ${Thread.currentThread().name}"

}