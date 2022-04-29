package com.wangweijun.myapplication.zhangtao.basic.unit2

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/28 23:41
 * version: 1.0
 * desc   : 生成类一个抽象类MyResult
 */
sealed class MyResult {
    data class Success(val reponse: String) : MyResult()

    data class Error(val error: String): MyResult()

    data class Loading(val loading: String): MyResult()
}
