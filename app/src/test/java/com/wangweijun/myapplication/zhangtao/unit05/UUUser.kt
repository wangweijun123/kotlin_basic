package com.wangweijun.myapplication.zhangtao.unit05
// 私有构造，外部无法调用
// 工厂模式
class UUUser private constructor(name: String){
    companion object { // 省略里面类名，无所谓
        // 统一检查，比如敏感词过滤
        fun create(name: String): UUUser {
            println("new UUUser")
            return UUUser(name) // 可以动手脚，搞单利
        }
    }
}