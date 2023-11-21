package com.wangweijun.myapplication.zhangtao.unit05

// in out 修饰泛型，整体原则是
// in修饰的泛型作为参数，out 作为 返回值，但是有特殊情况...
// 在特殊场景下，同时作为参数和返回值的泛型参数，我们可以用 @UnsafeVariance 来解决型变冲突
abstract class BaseSingleton2<in P, out T> {

    // 注意这个private, instance 本身生成了getter与setter方法，所以既是参数又是返回值
    @Volatile
    private var instance: T? = null
    // 属性: 类型是一个函数
    abstract val creator: (P) -> T
    //    protected abstract fun creator(param: P): T

    fun getInstance(param: P): T =
        instance ?: synchronized(this) {
            instance ?: creator(param).also {
                instance = it
            }
        }
}


class PersonManagerLast private constructor(var name: String) {
    companion object : BaseSingleton2<String, PersonManagerLast>() {
        //             报错，类型不匹配
        //                  ↓
//        override val creator = PersonManager(name)
//        override val creator: (String) -> PersonManagerLast = PersonManagerLast("xxx") // build error
        // :: 引用PersonManagerLast的构造函数
        override val creator: (String) -> PersonManagerLast = ::PersonManagerLast
    }
}

