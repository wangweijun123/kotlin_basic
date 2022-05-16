package com.wangweijun.myapplication.zhangtao.unit11.last.v1

import com.google.gson.Gson
import com.wangweijun.myapplication.zhangtao.unit11.annotations.KtHttpV1
import com.wangweijun.myapplication.zhangtao.unit11.annotations.MyField
import com.wangweijun.myapplication.zhangtao.unit11.annotations.MyGet
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/05/16 17:18
 * version: 1.0
 * desc   :
 */
object LastKtHttpV1 {
    var baseUrl = "https://trendings.herokuapp.com"
    private var okHttpClient = OkHttpClient()
    private var gson = Gson()



    fun <T> create(service: Class<T>): T {
        return Proxy.newProxyInstance(service.classLoader, arrayOf(service)
        ) { proxy, method, args ->
            // 方法上的所有注解
            val annotations = method.annotations
            for (annotation in annotations) {
                if (annotation is LastGet) {
                    val url = baseUrl + annotation.path
                    println("url = ${url}")
                    // lambda表达式返回
                    return@newProxyInstance invoke(url, method, args)
                }
            }
            return@newProxyInstance null
        } as T
    }

    private fun invoke(path: String, method: Method, args: Array<Any>): Any? {
        if (method.parameterAnnotations.size != args.size) return null

        var url = path
        val parameterAnnotations = method.parameterAnnotations
        for (i in parameterAnnotations.indices) {
            for (parameterAnnotation in parameterAnnotations[i]) {
                if (parameterAnnotation is LastFiled) {
                    val key = parameterAnnotation.value
                    val value = args[i].toString()
                    if (!url.contains("?")) {
                        url += "?$key=$value"
                    } else {
                        url += "&$key=$value"
                    }

                }
            }
        }
        println("last url = ${url}")
        val request = Request.Builder()
            .url(url)
            .build()
        // https://trendings.herokuapp.com/repo?lang=Kotlin&since=weekly
        val response =okHttpClient.newCall(request).execute()

        val genericReturnType = method.genericReturnType
        val body = response.body
        val json = body?.string()
        println(json)
        val result = gson.fromJson<Any?>(json, genericReturnType)
        return result
    }
}