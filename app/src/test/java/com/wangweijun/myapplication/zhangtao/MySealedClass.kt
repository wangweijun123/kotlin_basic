package com.wangweijun.myapplication.zhangtao

import com.wangweijun.myapplication.unit4.CompareResult

sealed class MySealedClass {
    object LESS : MySealedClass() {
        override fun toString(): String {
            return "小于"
        }
    }
    object MORE : MySealedClass() {
        override fun toString(): String {
            return "大于"
        }
    }
    object EQUAL : MySealedClass() {
        override fun toString(): String {
            return "等于"
        }
    }
}
