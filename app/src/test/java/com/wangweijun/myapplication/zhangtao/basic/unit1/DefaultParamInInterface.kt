package com.wangweijun.myapplication.zhangtao.basic.unit1

import org.junit.Test

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/06/19 09:35
 * version: 1.0
 * desc   :
 */

/**
 * 注意默认值设置在接口中
 */
interface InterfaceDx {
    fun setColor(color: Int = 0)

    fun setHeight(height: Int)
}

class InterfaceDxImpl : InterfaceDx {
    override fun setColor(color: Int) {
        println("color = $color")
    }

    override fun setHeight(height: Int) {
        println("height = $height")
    }

}

class DefaultParamInInterface {

    @Test
    fun testInterfaceParam() {
        val t = InterfaceDxImpl()
        t.setColor(100)
        // 有默认值,编译器有等号的哦, 无: 编译器不会有等号
        t.setColor()
        // 无: 编译器不会有等号
        t.setHeight(10000)
    }
}