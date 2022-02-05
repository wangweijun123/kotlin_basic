package com.wangweijun.myapplication.zhangtao.unit05

abstract class BaseSingleton<in P, out T> {
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