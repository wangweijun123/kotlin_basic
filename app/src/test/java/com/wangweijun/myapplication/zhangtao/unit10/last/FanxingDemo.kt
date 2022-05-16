package com.wangweijun.myapplication.zhangtao.unit10.last

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/05/16 00:40
 * version: 1.0
 * desc   :
 */
class FanxingDemo {

    //              逆变
//               ↓
    class Controller<in T> {
        //                 ①
//                 ↓
        fun turnOn(tv: T) {}
    }

    //               协变
//                ↓
    class Restaurant<out T> {
        //                   ②
//                   ↓
        fun orderFood(): T? { return null }
    }

}