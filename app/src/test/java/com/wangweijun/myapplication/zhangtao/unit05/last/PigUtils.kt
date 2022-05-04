package com.wangweijun.myapplication.zhangtao.unit05.last

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/10 4:23 下午
 * version: 1.0
 * desc   : // 这样可以写工具类， object 定义类，jvmstatic 修饰方法，
 * 1 会生成单利
 * 2 最终是调用实例的方法
 * 3 工具类其实可以直接写在kotlin文件中,函数被编译成了 public static final
 */
object PigUtils {
    //编译成类静态属性  private static int age;
    // 居然被编译成static静态变量,但是生成是实例方法
    var age = 100



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