package com.wangweijun.myapplication.zhangtao.unit09

import org.junit.Test

class Item {

    var count: Int = 0;

    // 中 total 的值与 count 完全一致
    // 属性委托,  1 并不是真正的多了一个属性，2 双向绑定的值，无论修改count或者total，其实都是操作count

    //                  属性的引用
    var total: Int by ::count
}

class TestItem {
    @Test
    fun testItem() {
        val item = Item()
        item.count = 10
        println("item.total = ${item.total}")

        item.total = 20
        println("item.count = ${item.count}")

    }
}