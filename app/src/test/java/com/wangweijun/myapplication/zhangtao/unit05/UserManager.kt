package com.wangweijun.myapplication.zhangtao.unit05

/**
 * 单例, static 静态代码块实例化
 */
object UserManager {
    // 对外暴露的 user, 属性上使用 by lazy 将s其包裹起来,实现懒加载, 同时来实现单利
    // 1 只要uuuser没有被使用，loadUser就不会被调用
    // 2 复用
    // 3 by lazy 实现懒加载：
    // 1 用时加载，2 缓存，如果有就不需要再加载
    // 还能保证线程安全
    val uuuser by lazy { loadUser() }

    private fun loadUser(): UUUser {
        println("loadUser ...")
        return UUUser.create("tom")
    }

    fun login() {
        println("login")
    }

    fun getUser() = uuuser
}