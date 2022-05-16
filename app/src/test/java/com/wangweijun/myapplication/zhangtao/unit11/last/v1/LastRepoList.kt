package com.wangweijun.myapplication.zhangtao.unit11.last.v1

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/05/16 17:13
 * version: 1.0
 * desc   :
 */
data class LastRepoList(
    //  定义成val，但是必须定义成？可空类型
    val count: Int?,
    val msg: String?,
    val items: List<LastRepo>?,
    // 定义的变量服务器没有返回会？？？ 1: 不会奔溃, 2有默认值 noFiled=0
    // 所以定义data class的属性，不要
    val noFiled: Int,
    // 返回是对象加？号，让他为可null类型
    val xxxx: MyPigx?
)

data class LastRepo(
    val added_stars: String?,
    val avatars: List<String>?,
    val desc: String?,
    val forks: String?,
    val lang: String?,
    val repo: String?,
    val repo_link: String?,
    val stars: String?
)

data class MyPigx (val name: String)
