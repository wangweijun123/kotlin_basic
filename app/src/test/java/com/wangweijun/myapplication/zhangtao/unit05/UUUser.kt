package com.wangweijun.myapplication.zhangtao.unit05
// 私有构造private constructor，外部无法调用
// 工厂模式
class UUUser private constructor(name: String){

    // 伴生对象：就是外部类拥有内部类的一个静态变量
    companion object { // 省略里面类名，无所谓
        // 统一检查，比如敏感词过滤
        fun create(name: String): UUUser {
            println("new UUUser")
            return UUUser(name) // 可以动手脚，搞单利
        }

        // JvmStatic
        @JvmStatic
        fun createJvmStatic(name: String): UUUser {
            println("new UUUser")
            return UUUser(name) // 可以动手脚，搞单利
        }
    }
}