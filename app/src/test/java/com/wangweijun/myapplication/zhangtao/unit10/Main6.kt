package com.wangweijun.myapplication.zhangtao.unit10

import org.junit.Test

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/03/20 3:59 下午
 * version: 1.0
 * desc   :
 */
class Main6 {

    @Test
    fun main() {
        val i = 2;
//        i = 3 // error

        val list = listOf(1, 2, 3, 4, 5)
//        list.add // error，不能增加删除元素
        var list2 = listOf(1, 2, 3, 4, 5)
//        list = list2  // error val 定义的变量不能重新附值

        val mutableListOf = mutableListOf<Int>()
        mutableListOf.add(2)
        val mutableListOf2 = mutableListOf<Int>()
//        mutableListOf = mutableListOf2 // error val 定义的变量不能重新附值
    }
}