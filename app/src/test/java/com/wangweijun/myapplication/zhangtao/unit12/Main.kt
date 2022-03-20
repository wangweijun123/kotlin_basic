package com.wangweijun.myapplication.zhangtao.unit12

import org.junit.Test

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/03/20 3:59 下午
 * version: 1.0
 * desc   :
 */
class Main {

    @Test
    fun main() {
        testExp(3)
        testExp("duanxia")
    }

    fun testExp(data: Any) {
        val i = when (data) {
            is Number -> data.toInt()
            is String -> data.length
            else -> { 0 }
        }
        println(i)
    }

    @Test
    fun main2() {
        val myPPP = MyPPP("dx", 18)
        println(myPPP.hashCode())
        val p2 = changeUserName(myPPP, "wxj")
        println(p2.hashCode()) //
        println(p2.name)
    }

    private fun changeUserName(ppp: MyPPP, newName: String): MyPPP {
//        ppp.name = newName // error
        return ppp.copy(name = newName) // 产生新对象
    }

    data class MyPPP(
        val name: String,
        val age: Int
    )

}