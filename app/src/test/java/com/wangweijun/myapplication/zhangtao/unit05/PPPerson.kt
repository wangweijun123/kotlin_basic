package com.wangweijun.myapplication.zhangtao.unit05

class PPPerson {
    // 嵌套类是单例模型
    object InnerSingleTon {
        fun foo() {
            println("foo")
        }
    }

    object InnerSingleTon2 {
        // 使用注解使方法变成了static方法,这样不过是在java或kotlin文件
        // 都是统一调用
        @JvmStatic
        fun foo2() {
            println("foo2")
        }
    }

    companion object InnerSingleTon3 {
        // 加上了companion,这个static方法会挪动到外部类中,变成静态方法
        @JvmStatic
        fun foo3() {
            println("foo3")
        }
    }

}