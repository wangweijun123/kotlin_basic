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

        val str2 = """ 
{
  "type": "onfido",
  "asyncReqCount": "10",
  "asyncReqSeconds": "3",
  "token": "xxxx",
  "enableNFC": "true",
  "face_type": "video",
  "documentType": "['ID_CARD', 'PASSPORT', 'DRIVERS_LICENSE']",
  "countryCode": "GB"
}
"""
    }

    @Test
    fun testJson() {

    }

}