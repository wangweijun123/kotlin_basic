package com.wangweijun.myapplication.zhangtao.unit3

import org.json.JSONObject
import org.junit.Test

class NullOrEmptyTest {

    @Test
    fun testEnum() {
        var str: String? = null
        isNullOrBlank2(str)
    }

    fun isNullOrBlank2(str: String?) {
        if (!str.isNullOrBlank()) {
            println("#######")
        } else {
            println("isNullOrBlank")
        }
    }

//    isNullOrEmpty
    @Test
    fun testList() {
        var documentTypes: ArrayList<String>? = null
        if (!documentTypes.isNullOrEmpty()) {
            println(" not empty")
        } else {
            println(" is Null Or Empty")
        }
    }

    @Test
    fun testForIn() {
        for (i in 1..4) { // 'downTo' 使用替换
            println("$i ")
        }
        println("测试小的 ")
        for (i in 1..1) { // 'downTo' 使用替换
            println("$i ")
        }
    }

    @Test
    fun testuntil() {
        val length = 5
        for (i in 0 until length) {
            print("$i ") // 不包括5
        }
    }

    @Test
    fun testJson() {
        var timeInt = 5
        if (timeInt in 1..4) {
            println("$timeInt ")
        } else {
            println(" 不在这个范围 ")
        }
    }

    private fun getSafeTime(time: Long?, defaultTime: Long): Long {
        if (time == null) {
            return defaultTime
        }
        return if (time in 0..300) time else defaultTime
    }

    /*private fun getSafeTim3(time: Long?, defaultTime: Long): Long {
        time?:
        return if (time in 0..300) time else defaultTime
    }*/

//    private fun getSafeTime2(time: Long?, defaultTime: Long): Long {
//        var timeInt = time
//
//        return if (timeInt in 0..300) timeInt!! else defaultTime
//    }

    fun testSet() {
        var filterList: MutableSet<String>? = mutableSetOf()
        filterList?.contains("item")

    }

    @Test
    fun testStringEmpty() {
//        val str:CharSequence? = "xx";
        //
        val str:CharSequence? = null;
        if (str?.isEmpty() == true) {  // 空字符串
            println(" true ")
        } else { // 值 null 或 有值 xxx
            println(" false ")
        }
        println(" str.isNullOrEmpty() ? ${str.isNullOrEmpty()} ")

    }
}