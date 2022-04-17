package com.wangweijun.myapplication.zhangtao.unit05.last

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/10 4:23 下午
 * version: 1.0
 * desc   : // 这样可以写工具类
 */
object PigUtils {
    //编译成类静态属性  private static int age;
    private var age = 100



    // 翻译成静态static 方法
    @JvmStatic
    fun getPigs(){
        println("getPigs , $age")
    }

    // 不是静态方法，但是实例方法可以访问age这个静态属性呀
    fun noJvmStatic() {
        println("noJvmStatic , $age")
    }
}