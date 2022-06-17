package com.wangweijun.myapplication.zhangtao.basic.unit1

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.junit.Assert
import org.junit.Test
import java.lang.Exception

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/10 12:09 上午
 * version: 1.0
 * desc   : kotlin 中一切都是对象
 */
// kotlin中class 默认 public final ，反编译看
// 1 第一 class没法集成，
class BasiTypeDemo {

    // public final，默认没法复写
    fun test01() {
        println("test01")
    }
    @Test
    fun test02() {
        val i = 1.toDouble() // 1 是 Int 类型，调用了方法
    }

    @Test
    fun test03() {
        val result = myAdd(1, 3)
        println("myAdd = $result")
    }

    @Test
    fun testNullToString() {
        val name: String? = null
        println(name.toString())
        Assert.assertEquals("null", name.toString())
        println(name.toString())
    }

    @Test
    fun testNull() {
        val kycCommonConfigResp = KycCommonConfigResp(FaceKycAndroidConfig("1a"))
        success(kycCommonConfigResp)
    }

    fun success(result: KycCommonConfigResp?) {
        if (result?.faceKycAndroidConfig?.abTestId != null) {
            val abTestId = try {
                result.faceKycAndroidConfig.abTestId.toInt()
            } catch (e: Exception) {
                println("catch Exception")
                return
            }
            println("not null abTestId = $abTestId")
        } else {
            println("is null")
        }
    }

    data class KycCommonConfigResp(
        val faceKycAndroidConfig: FaceKycAndroidConfig?
    )

    data class FaceKycAndroidConfig(
        val abTestId: String?
    )
}