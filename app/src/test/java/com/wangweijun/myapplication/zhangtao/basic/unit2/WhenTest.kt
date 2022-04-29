package com.wangweijun.myapplication.zhangtao.basic.unit2

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/29 00:55
 * version: 1.0
 * desc   :
 */
class WhenTest {

    fun testExp(data: Any) {
        val i = when (data) {
            is Number -> data.toInt()
            is String -> data.length
            else -> { 0 }
        }
        println(i)
    }
}