package com.wangweijun.myapplication.zhangtao.unit11.annotations

import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Response
import java.io.IOException
import java.lang.Exception
import java.lang.reflect.Type

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/04 6:33 下午
 * version: 1.0
 * desc   :
 */
class KtCall<T: Any>(
    private val call: Call,
    private val gson: Gson,
    private val type: Type
) {
    fun call(callback: Callback<T>): Call {
        // 步骤1， 使用call请求API
        // 步骤2， 根据请求结果，调用callback.onSuccess()或者是callback.onFail()
        // 步骤3， 返回OkHttp的Call对象
        call.enqueue(object : okhttp3.Callback{
            override fun onFailure(call: Call, e: IOException) {
                callback.onFail(e)
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val t = gson.fromJson<T>(response.body?.string(), type)
                    callback.onSuccess(t)
                } catch (e: Exception) {
                    callback.onFail(e)
                }
            }
        })
        return call
    }

}