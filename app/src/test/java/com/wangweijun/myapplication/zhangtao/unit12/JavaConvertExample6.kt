package com.wangweijun.myapplication.zhangtao.unit12

import org.junit.Test

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/03/20 6:18 下午
 * version: 1.0
 * desc   :
 */

/**
 * 定义不为空对象，使用by lazy 方式初始化，使用的地方直接用，都不用关心空指针
 * @property name String
 */
class JavaConvertExample6 {
    // 这种初始化方式我感觉是最好的 by lazy
    private val name: String by lazy { init() }

    fun init(): String { // 这里只会初始化一遍
        println("init name= tom")
       return "tom"
    }

    fun test() {
        val count = name.length
        println("count=$count")
    }

}