package com.wangweijun.myapplication.zhangtao.unit11.annotations

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.reflect.Method
import java.lang.reflect.Proxy

/**
 * 任何支持Get API
 * 以 GitHub Trendings API 为例：
 * https://trendings.herokuapp.com/repo?lang=java&since=weekly
 */
interface ApiService {
    @MyGet("/repo")
    fun repos(
        @MyField("lang") lang: String,
        @MyField("since") since: String
    ): RepoList
}


interface GitHubService {
    @MyGet("/search")
    fun search(
        @MyField("id") id: String
    ): MyUser
}

data class MyUser(val id: String)


data class RepoList(
    var count: Int?,
    var items: List<Repo>?,
    var msg: String?
)

data class Repo(
    var added_stars: String?,
    var avatars: List<String>?,
    var desc: String?,
    var forks: String?,
    var lang: String?,
    var repo: String?,
    var repo_link: String?,
    var stars: String?
)

/**
 * 单例
 */
object KtHttpV1 {
    private var okHttpClient = OkHttpClient()
    private var gson = Gson()
    // https://api.github.com/?lang=Kotlin&since=weekly


    var baseUrl = "https://trendings.herokuapp.com"
    // java 中 的object == kotlin中的Any
    // InvocationHandler 接口只有一个方法，InvocationHandler 符合sam转换 ，使用lambda表示
    // as 强转
    fun <T> create(service: Class<T>): T {
        return Proxy.newProxyInstance(
            service.classLoader,
            arrayOf<Class<*>>(service)
        ) { proxy, method, args ->
            // 方法上的所有注解
            val annotations = method.annotations
            for (annotation in annotations) {
                if (annotation is MyGet) {
                    val url = baseUrl + annotation.path
                    // lambda表达式返回
                    return@newProxyInstance invoke(url, method, args)
                }
            }
            return@newProxyInstance null
        } as T // 转换成了 T 类型
    }

    private fun invoke(path: String, method: Method, args: Array<Any>): Any? {
        if (method.parameterAnnotations.size != args.size) return null

        var url = path
        val parameterAnnotations = method.parameterAnnotations
        for (i in parameterAnnotations.indices) {
            for (parameterAnnotation in parameterAnnotations[i]) {
                if (parameterAnnotation is MyField) {
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

        val request = Request.Builder()
            .url(url)
            .build()
        // https://trendings.herokuapp.com/repo?lang=Kotlin&since=weekly
        val response = okHttpClient.newCall(request).execute()

        val genericReturnType = method.genericReturnType
        val body = response.body
        val json = body?.string()
        println("json = $json")
        val result = gson.fromJson<Any?>(json, genericReturnType)

        return result
    }


}


fun testOkhttpClient() {
    val api: ApiService = KtHttpV1.create(ApiService::class.java)
    val data: RepoList = api.repos(lang = "Kotlin", since = "weekly")
    println(data)
}





