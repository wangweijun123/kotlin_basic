package com.wangweijun.myapplication.zhangtao.unit05.last

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/10 4:50 下午
 * version: 1.0
 * desc   :
 */
class OutterClass {

    fun useInner() {
        println(TAG)
    }

    companion object {
        // 这个常量被移动到外部的类OutterClass中了
        const val TAG = "xxxx"
    }
}