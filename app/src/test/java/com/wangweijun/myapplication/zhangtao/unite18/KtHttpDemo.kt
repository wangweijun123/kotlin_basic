package com.wangweijun.myapplication.zhangtao.unite18

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.Test
import kotlin.coroutines.suspendCoroutine

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/04 1:58 下午
 * version: 1.0
 * desc   :
 */
class KtHttpDemo {
    data class MyBean(
        var name: String,
        var url: String
    )

    @Test
    fun testParseClass() {
        val classJson = """
            {
                "name": "duanxi",
                "url": "http://www.bejson.com"
            }
        """.trimIndent()

        val gson = Gson()
        val fromJson = gson.fromJson(classJson, MyBean::class.java)
        println("fromJson: $fromJson")
    }

    @Test
    fun testParseClass2() {
        val classJson = """
            {
                "name": "duanxi",
                "url": "http://www.bejson.com"
            }
        """.trimIndent()

//        val gson = Gson()
//                val typeOfT = TypeToken<Collection<Foo>>(){}.getType();
//        val fromJson = gson.fromJson(classJson, MyBean::class.java)
//        println("fromJson: $fromJson")

    }
}