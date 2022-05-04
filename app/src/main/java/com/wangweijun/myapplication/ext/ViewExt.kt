package com.wangweijun.myapplication.ext

import android.view.View
import android.view.ViewGroup

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/05/04 23:22
 * version: 1.0
 * desc   :
 */
// 给 View.java 增加扩展函数
inline fun <reified T : ViewGroup.LayoutParams> View.updateLayoutParamsMe(block: T.() -> Unit) {
    val params = layoutParams as T
    block(params)
    layoutParams = params
}

fun View.updateMargin(left: Int? = null, top: Int? = null, right: Int? = null, bottom: Int? = null) {
    (layoutParams as? ViewGroup.MarginLayoutParams)?.let {
        updateLayoutParamsMe<ViewGroup.MarginLayoutParams> {
            left?.let {
                marginStart = it
            }

            right?.let {
                marginEnd = it
            }
            // ...
        }
    }
}
