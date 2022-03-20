package com.wangweijun.myapplication.zhangtao.unit12

import org.junit.Test

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/03/20 6:18 下午
 * version: 1.0
 * desc   :
 */

class JavaConvertExample4 {
    // 可空类型 val 变量
    private var name: String? = null

    fun test() {
        name?.let {
            it.length
        }
    }

}