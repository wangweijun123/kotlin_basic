package com.wangweijun.myapplication.zhangtao.unit11.last.v1

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/05/16 17:02
 * version: 1.0
 * desc   :
 */

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class LastGet(val path: String)
