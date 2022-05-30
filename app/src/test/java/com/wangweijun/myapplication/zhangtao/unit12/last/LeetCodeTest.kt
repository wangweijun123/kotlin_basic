package com.wangweijun.myapplication.zhangtao.unit12.last

import org.junit.Test

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/05/28 11:15
 * version: 1.0
 * desc   :
 */
class LeetCodeTest {
    @Test
    fun testString() {
        val s = "abcdefgiouduanxia"
        //      bcdfgdnx
        println(removeVowels(s))
    }

    fun removeVowels(s: String): String =
        s.filter { it !in setOf('a', 'e', 'i', 'o', 'u') }
}