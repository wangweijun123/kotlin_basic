package com.wangweijun.myapplication.zhangtao.unit11.annotations

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/02/19 3:57 下午
 * version: 1.0
 * desc   :
 */
@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class MyField(val value: String)
