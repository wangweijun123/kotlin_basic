package com.wangweijun.myapplication.unit7

fun loop() {
    for (i in 1..10) {
        print("$i ")
    }
    println()
    println("-------------")

    for (i in 10..1) { // 'downTo' 使用替换
        print("$i ")
    }
    println()
    println("-------------")

    for (i in 1 until 10) {
        print("$i ") // 不包括10
    }
    println()
    println("-------------")

    for (i in 1 until 10 step 2) {
        print("$i ") // 不包括10
    }
    println()
    println("-------------")

    val arrayList = arrayListOf<String>("a", "b", "c", "d")
    for (item in arrayList) {
        print("$item ")
    }
    println()
    println("-------------")

    val arrayList2 = arrayListOf<String>("a", "b", "c", "d")
    for ((index, item) in arrayList2.withIndex()) {
        print("第$index 个元素 值为$item #")
    }
    println()
    println("-------------")

    // 如下三种都是一样的结果
    repeat(10, {index: Int ->
        print("$index ")
    })
    println()
    println("-------------")
    repeat(10){index: Int ->
        print("$index ")
    }
    println()
    println("-------------")
    repeat(10){
        print("$it ")
    }
    println()
    println("-------------")
}