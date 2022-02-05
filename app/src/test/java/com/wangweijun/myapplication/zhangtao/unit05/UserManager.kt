package com.wangweijun.myapplication.zhangtao.unit05

/**
 * 单例
 */
object UserManager {
    // 对外暴露的 user, 属性上使用 by lazy 将s其包裹起来,实现懒加载
    // 1 只要uuuser没有被使用，loadUser就不会被调用
    // 2 复用
    val uuuser by lazy { loadUser() }

    private fun loadUser(): UUUser {
        return UUUser.create("tom")
    }

    fun login() {
        println("login")
    }
}