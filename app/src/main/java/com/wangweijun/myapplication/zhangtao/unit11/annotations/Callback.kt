package com.wangweijun.myapplication.zhangtao.unit11.annotations

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/04 6:31 下午
 * version: 1.0
 * desc   :
 */
// 泛型边界“T: Any”, 保证T非空
interface Callback<T: Any> {
    fun onSuccess(data: T)
    fun onFail(throwable: Throwable)
}