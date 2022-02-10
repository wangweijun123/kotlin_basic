package com.wangweijun.myapplication.zhangtao

sealed class MyResult<out R> {
    data class Success<out T>(val data: T, val message: String = "") : ZT02.Result<T>()

    data class Error(val exception: Exception) : ZT02.Result<Nothing>()

    data class Loading(val time: Long = System.currentTimeMillis()) : ZT02.Result<Nothing>()
}