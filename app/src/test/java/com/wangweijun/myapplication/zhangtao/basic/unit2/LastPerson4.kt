package com.wangweijun.myapplication.zhangtao.basic.unit2

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/10 9:20 上午
 * version: 1.0
 * desc   :
 */
class LastPerson4(val name: String, var age: Int) {
    // 这是真正增加了属性, 但是是一次性附值，所以改变age，是没法改变isAdult的值，注意啊
    val isAdult = age > 18
}