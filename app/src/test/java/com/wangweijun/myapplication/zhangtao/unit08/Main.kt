package com.wangweijun.myapplication.zhangtao.unit08

import org.junit.Test

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/02/23 1:44 上午
 * version: 1.0
 * desc   :
 */
class Main {
    @Test
    fun testSort() {
        val originList = listOf(
            WordFreq("b", 2),
            WordFreq("a", 1),
            WordFreq("c", 3)
        )
        // for 循环迭代数组或者list，
//        originList.size
        for (item in originList) {
            println("$item")
        }
        println(originList)
        val newList = originList.sortedByDescending { it.frequency }
        println("倒序 = $newList")
        val newList2 = originList.sortedBy { it.frequency }
        println("升序 = $newList2")
        val newList3 = originList.sortedBy { it.frequency == 1}
        println("什么鬼 = $newList3")
    }

    @Test
    fun testMapIndexed() {
        val originList = listOf(
            WordFreq("b", 2),
            WordFreq("a", 1),
            WordFreq("c", 3)
        )
        println(originList)
        // map 转换
        val map = originList.map { it.frequency }
        println("map = $map") // [2, 1, 3]
        val map2 = originList.mapIndexed{ index, item ->
            item.word.plus(index)
//            item
        }
        println("mapIndex = $map2") // [b0, a1, c2]
    }

}