package com.wangweijun.myapplication.zhangtao.unit11.annotations

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.reflect.Method
import java.lang.reflect.Proxy

fun testOkhttpClient2() {
    // create() 函数的范型由调用端定义的变量类型决定ApiService2决定哈
    // 居然定义一个变量的类型就能生产这个类型的对象
    val api: ApiService2 = KtHttpV2.create()
    val data: RepoList = api.repos(lang = "Kotlin", since = "weekly")
    println(data)
}

interface ApiService2 {
    @MyGet("/repo")
    fun repos(
        @MyField("lang") lang: String,
        @MyField("since") since: String
    ): RepoList
}
/**
 * 单例
 */
object KtHttpV2 {
    // by lazy实现懒加载, 只能用于val变量
    private val okHttpClient by lazy { OkHttpClient() }
    private val gson by lazy { Gson() }
    var baseUrl = "https://trendings.herokuapp.com"
    // java 中 的object == kotlin中的Any
    // InvocationHandler 接口只有一个方法，InvocationHandler 符合sam转换 ，使用lambda表示
    // as 强转
    // inline reified联合使用，来实现类型实化
    inline fun <reified T> create(): T {
        return Proxy.newProxyInstance(
            T::class.java.classLoader, // ① 变化在这里
            arrayOf(T::class.java) //  变化在这里
        ) { proxy, method, args ->
            // 方法上的所有注解
            return@newProxyInstance method.annotations
                .filterIsInstance<MyGet>()
                .takeIf { it.size == 1 }
                ?.let { invoke2("$baseUrl${it[0].path}", method, args) }
        } as T // 转换成了 T 类型
    }

    fun invoke2(url: String, method: Method, args: Array<Any>): Any? =
        method.parameterAnnotations
            .takeIf { it.size == args.size }
            ?.mapIndexed { index, it -> Pair(it, args[index])}
            ?.fold(url, ::parseUrl) // 函数引用的语法
            ?.let { Request.Builder().url(it).build() }
            ?.let { okHttpClient.newCall(it).execute().body?.string() }
            ?.let { gson.fromJson(it, method.genericReturnType) }

    private fun parseUrl(acc: String, pair: Pair<Array<Annotation>, Any>) =
        pair.first.filterIsInstance<MyField>()
            .first() // 取出第一个 Field 注解，这里它也应该是唯一的
            .let { field ->
                if (acc.contains("?")) {
                    "$acc&${field.value}=${pair.second}"
                } else {
                    "$acc?${field.value}=${pair.second}"
                }
            }
}

