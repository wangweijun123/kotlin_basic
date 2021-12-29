package com.wangweijun.myapplication

class Single

class StringUtils /*private constructor()*/ {

//    @JvmStatic is error
    //  objects and companion objects can be annotated with '@JvmStatic
    // 只有object 或者 companion objects 里面的成员才能使用JvmStatic

    // 这个是实例方法, 需要实例化才能调用
    fun isEmp(str: String): Boolean {
        return "".equals(str)
    }

    // 方便调用 StringUtils.isEmp2("xxxx), 相当于java中public static 方法
    companion object {
        fun isEmp2(str: String): Boolean {
            return "".equals(str)
        }
    }
}