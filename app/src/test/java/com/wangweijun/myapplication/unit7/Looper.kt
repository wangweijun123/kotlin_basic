package com.wangweijun.myapplication.unit7
// Kotlin是原文件, 编译成class, jadx反编译class成java代码, ASM(Javap)看的是字节码
fun loop() {
    // 运算符重载, 使用operator, .. 与 rangeTo 与这个函数映射
    // .. 其实调用了函数 operator fun rangeTo
    // public operator fun rangeTo(other: Int): IntRange
    for (i in 1..10) {
        print("$i ")
    }
    println()
    // + 也是运算符重载 在Kotlin String类中
    // public operator fun plus(other: Any?): String
    println("-------------" + "xxx")

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
    // until return IntRange
    // fun IntProgression.step
    // IntRange 继承  IntProgression , 而step 是 IntProgression的扩展函数
    // 蓝色是关键字,比如in val, 绿色是其实是一个函数
    val aa:Int = 2
    // 都是修饰了函数 fun
    // infix fun 中缀表达式
    // operator fun 运算符重载
    // step 是重罪 public infix fun IntProgression.step(step: Int): IntProgression
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