package com.wangweijun.myapplication.zhangtao.unit05.last

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/10 4:09 下午
 * version: 1.0
 * desc   :
 */
class PPPPPerson {
    // PPPPPerson 还是一样的
    // 这个object定义的类被编译成了static的单利
    // public static final class InnerSingleton
    object InnerSingleton {
        fun foo() {} // 注意这里还是实例方法

        // static 方法
        @JvmStatic
        fun foo2() {} // 注意这里是java中的静态方法
    }
}