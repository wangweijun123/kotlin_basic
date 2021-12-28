package com.wangweijun.myapplication

import com.wangweijun.myapplication.AInterface
import com.wangweijun.myapplication.B

class B : AInterface {
    override fun putNumber(num: Int) {
        println("is int")
    }

    // public static final BOld a = new BOld();
    // 这相当于在JAVA中静态的实例
    companion object {
        val a = B()
    }
}