package com.wangweijun.myapplication.zhangtao.unit11

import com.wangweijun.myapplication.zhangtao.unit11.annotations.KtHttpV1
import com.wangweijun.myapplication.zhangtao.unit11.annotations.testOkhttpClient
import com.wangweijun.myapplication.zhangtao.unit11.annotations.testOkhttpClient2
import org.junit.Test

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/02/19 4:29 下午
 * version: 1.0
 * desc   :
 */
class HttpTest {

    @Test
    fun testHttp() {
        testOkhttpClient()
    }

    @Test
    fun testHttp2() {
        testOkhttpClient2()
    }
}