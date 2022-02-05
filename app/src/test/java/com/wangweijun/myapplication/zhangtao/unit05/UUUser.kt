package com.wangweijun.myapplication.zhangtao.unit05
// 私有构造，外部无法调用
class UUUser private constructor(name: String){
    companion object {
        // 统一检查，比如敏感词过滤
        fun create(name: String): UUUser {
            println("new UUUser")
            return UUUser(name)
        }
    }
}