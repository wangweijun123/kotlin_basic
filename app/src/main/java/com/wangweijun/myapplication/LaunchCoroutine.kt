package com.wangweijun.myapplication

import android.provider.Contacts
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import java.net.URL

/*// 用于执行协程任务
fun launchCoroutine1() = launch {

}*/

fun launchCoroutine2() = runBlocking {

}

private val mOkHttpClient = OkHttpClient()

fun dispalyDashboard() = runBlocking {
}

//suspend,  被suspend修饰的函数只能被有 suspend 修饰的函数调用
//因为suspend修饰的函数(或lambda)被编译后会多一个参数类型叫Continuation，
//协程的异步调用本质上就是一次回调
/*suspend fun getHtml(): String {
    return async(AndroidCommonPool) { URL("http://baidu.com").readText() }.await()
}*/
