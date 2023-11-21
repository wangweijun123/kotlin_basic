package com.wangweijun.myapplication.zhangtao.unit09.last

import org.junit.Test
import kotlin.reflect.KProperty

class StringDelegate(private var s: String = "Hello") {
    //     ①                           ②                              ③
//     ↓     必须有 operator 关键字修饰                       ↓                               ↓
    operator fun getValue(thisRef: Owner, property: KProperty<*>): String {
        println("getValue")
        return s
    }
    //      ①                          ②                                     ③
//      ↓                           ↓                                      ↓
    operator fun setValue(thisRef: Owner, property: KProperty<*>, value: String) {
        println("setValue")
        s = value
    }
}

//      ②
//      ↓
class Owner {
    // by 委托， lazy 指的是加载方法： 懒
    // text属性委托给StringDelegate对象的getValue方法
    //               ③
//               ↓
    var text: String by StringDelegate()
}

class TestOwner {
    @Test
    fun test() {
        val owner = Owner()
        println(owner.text)
        owner.text = "world"
        println(owner.text)
    }
}