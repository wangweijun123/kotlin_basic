package com.wangweijun.myapplication.zhangtao.unit12.last

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/05/17 00:43
 * version: 1.0
 * desc   : java bean 标准写法
 */
data class LastJavaBean(
    val name: String?,
    val count: Int?,
    val child: LastChild?
)

data class LastChild(
    val age: Int?
)