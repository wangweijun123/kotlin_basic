package com.wangweijun.myapplication.zhangtao

import android.content.Context
import android.content.Intent
import com.wangweijun.myapplication.MainActivity

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/05/08 11:27
 * version: 1.0
 * desc   :
 */

// 简化了页面跳转~
private const val FRIEND_ID = "friendId"

fun callGoto(mContext: Context, userId: Long) {
    mContext.goto<MainActivity> {
        putExtra(FRIEND_ID, userId)
    }
}

// 接收者函数，就是接收者的成员方法
inline fun <reified T> Context.goto(block: Intent.() -> Unit) {
    this.startActivity(intent<T>(this, block))
}

inline fun <reified T> intent(mContext: Context, block: Intent.() -> Unit): Intent {
    val intent = Intent(mContext, T::class.java)
    intent.block()
    return intent
}