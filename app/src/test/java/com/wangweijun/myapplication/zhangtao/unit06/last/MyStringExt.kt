package com.wangweijun.myapplication.zhangtao.unit06.last

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/05/04 23:38
 * version: 1.0
 * desc   :
 */


// 不为空的接收者类型
//     ↓
fun String.lastElement2(): Char? {
    if (length == 0) {
        return null
    }
    return this[length-1]
}

// 可为空的接收者类型
//     ↓
fun String?.lastElement3(): Char? {
    // 接受者可为空类型，里面的this必须判断空
    if (this == null || this.isEmpty()) {
        return null
    }
    return this[length - 1]
}