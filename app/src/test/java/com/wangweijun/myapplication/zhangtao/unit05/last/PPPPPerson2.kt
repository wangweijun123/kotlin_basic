package com.wangweijun.myapplication.zhangtao.unit05.last

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/10 4:28 下午
 * version: 1.0
 * desc   : PPPPPerson2 拥有静态内部类的一个实例
 */
class PPPPPerson2 {
    // companion object 与 object 区别： companion object外部调用更加方便而已
    companion object InnerSingleton {
        fun foo() {} // 注意这里还是实例方法

        @JvmStatic
        fun foo2() {} // 注意这里还是实例方法
    }
}