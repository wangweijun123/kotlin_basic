package com.wangweijun.myapplication.zhangtao.unit11.annotations

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/02/19 1:41 下午
 * version: 1.0
 * desc   :
 */

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class MyGet(val path: String)