package com.wangweijun.myapplication.zhangtao.unit11.last.v1

import org.junit.Test

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/05/16 17:29
 * version: 1.0
 * desc   :
 */
class LastDemo {

    @Test
    fun test() {
        val lastApiService: LastApiService = LastKtHttpV1.create(LastApiService::class.java)
        println(lastApiService.javaClass.simpleName)
        val lastRepoList: LastRepoList = lastApiService.repos(since = "since", lang = "Kotlin")
        println(lastRepoList)
        println(lastRepoList.xxxx?.name)
    }
}