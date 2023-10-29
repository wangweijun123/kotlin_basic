package com.wangweijun.myapplication.zhangtao.unit05

class PPPerson {



    //静态内部类,并且InnerSingleTon嵌套类是单例模型
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

    // companion object 伴生对象,就是产生外部对象的同时，产生一个内部对象，
    // 因为外部类有一个内部类InnerSingleTon3的静态属性
    // 这个类名字可以省略，直接写companion object
    companion object InnerSingleTon3 {
        // 加上了companion,这个static方法会挪动到外部类中,变成静态方法
        @JvmStatic
        fun foo3() {
            println("foo3")
        }
    }

}