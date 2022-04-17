package com.wangweijun.myapplication.zhangtao.basic.unit2


/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/10 9:48 上午
 * version: 1.0
 * desc   : 嵌套的类
 */
class LastA {
    val lastName: String = ""

    // 被翻译成了 static class, 静态的内部类
    class LastB {
        fun t() {
//            val my = lastName // build error,无法外部类的成员
        }
    }
}