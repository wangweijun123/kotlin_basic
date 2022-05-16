package com.wangweijun.myapplication.zhangtao.unit11.last.v1

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/05/16 17:08
 * version: 1.0
 * desc   :
 */
interface LastApiService {
    @LastGet("/repo")
    fun repos(
        @LastFiled("lang") lang: String,
        @LastFiled("since") since: String
    ): LastRepoList
}