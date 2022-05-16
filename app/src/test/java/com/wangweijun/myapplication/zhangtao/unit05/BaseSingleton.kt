package com.wangweijun.myapplication.zhangtao.unit05

// in out 修饰泛型，整体原则是
// in修饰的泛型作为参数，out 作为 返回值，但是有特殊情况...
// 在特殊场景下，同时作为参数和返回值的泛型参数，我们可以用 @UnsafeVariance 来解决型变冲突
abstract class BaseSingleton<in P, out T> {
    // 注意private,去掉是不行滴
    @Volatile
    private var instance: T? = null

    fun getInstance(param: P): T =
        instance ?: synchronized(this) {
            instance ?: creator(param).also {
                instance = it
            }
        }

    protected abstract fun creator(param: P): T
}