package com.wangweijun.myapplication.zhangtao.basic.unit2

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/10 9:20 上午
 * version: 1.0
 * desc   :
 */
class LastPerson5(val name: String) {
    var age: Int = 0
        get() {
            println("get ...")
            return age
        }
        set(value) {
            println("set ...")
            field = value
        }
}