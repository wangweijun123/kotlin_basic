package com.wangweijun.myapplication.zhangtao.unit09.last

import org.junit.Test

class DelegateDemo {
    var count = 1
    // 属性total委托 count: total的值与count保持一直一致
    // 原理，就是total的get set方法，调用的是count属性
    var total by ::count

    @Test
    fun test() {
        val delegateDemo = DelegateDemo()
        println(delegateDemo.total)
        println(delegateDemo.count)
//        delegateDemo.count = 100
        delegateDemo.total = 100
        println(delegateDemo.total)
        println(delegateDemo.count)
    }
}