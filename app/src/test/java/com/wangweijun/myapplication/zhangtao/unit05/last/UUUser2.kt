package com.wangweijun.myapplication.zhangtao.unit05.last

import com.wangweijun.myapplication.zhangtao.unit05.UUUser

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/10 4:43 下午
 * version: 1.0
 * desc   :
 */
class UUUser2 private constructor(name: String){
    companion object InnerClass {
        // 统一检查，比如敏感词过滤
        fun create(name: String): UUUser2 {
            println("new UUUser")
            return UUUser2(name)
        }
    }
}