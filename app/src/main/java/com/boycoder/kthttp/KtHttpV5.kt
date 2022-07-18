package com.boycoder.kthttp

import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/07/18 15:47
 * version: 1.0
 * desc   :
 */

suspend fun <T : Any> KtCall<T>.awaitLast(): T =
    suspendCoroutine { continuation ->
        call(object : Callback<T> {
            override fun onSuccess(data: T) {
                println("Request success!")
                continuation.resume(data)
            }

            override fun onFail(throwable: Throwable) {
                println("Request fail!：$throwable")
                continuation.resumeWithException(throwable)
            }
        })
    }