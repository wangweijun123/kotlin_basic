package com.wangweijun.myapplication.mycoroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

const val loginUrl = "https://example.com/login"

class LoginRepository(val responseParser: LoginResponseParser) {

    /**
     * 版本 1
     */
    fun makeLoginRequest(jsonBody: String): Result<LoginResponse> {
        val url = URL(loginUrl)
        (url.openConnection() as? HttpURLConnection)?.run {
            // this 为 HttpURLConnection,代码块中可以调用HttpURLConnection
            requestMethod = "POST"
            setRequestProperty("Content-Type", "application/json; utf-8")
            setRequestProperty("Accept", "application/json")
            doOutput = true
            outputStream.write(jsonBody.toByteArray())
            return Result.Success(responseParser.parse(inputStream))
        }
        return Result.Error(Exception("Cannot open HttpURLConnection"))
    }

    /**
     * 版本 2
     * suspend解释: Kotlin 利用此关键字强制从协程内调用函数。
     * 想使用withContext必须使用suspend
     */
    suspend fun makeLoginRequest2(jsonBody: String): Result<LoginResponse> {
        // Move the execution of the coroutine to the I/O dispatcher
        // 将协程的执行操作移至一个 I/O 线程
        return withContext(Dispatchers.IO) {
            // Blocking network request code
            val url = URL(loginUrl)
            (url.openConnection() as? HttpURLConnection)?.run {
                // this 为 HttpURLConnection,代码块中可以调用HttpURLConnection
                requestMethod = "POST"
                setRequestProperty("Content-Type", "application/json; utf-8")
                setRequestProperty("Accept", "application/json")
                doOutput = true
                outputStream.write(jsonBody.toByteArray())
                Result.Success(responseParser.parse(inputStream))
            }
            Result.Error(Exception("Cannot open HttpURLConnection"))
        }

    }
}