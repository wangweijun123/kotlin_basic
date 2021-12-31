package com.wangweijun.myapplication.unit9

/**
 * kotlin 定义接口
 */
interface Callback {
    fun callback()
}

/**
 * 在kotlin class中限定泛型T使用where 关键词
 * kotlin 真泛型, java的泛型在编译器擦除
 */
class GenericTest<T> where T : Callback, T : Runnable{
    fun execute(t: T) {
        t.callback()
        t.run()
    }
}

class AA : Callback, Runnable{
    override fun callback() {
        println("call back ...")
    }

    override fun run() {
        println("run ...")
    }
}

open class BB1 : Callback {
    override fun callback() {
        println("BB1 callback...")
    }
}

/**
 * 只能继承一个class,与java一致
 */
class BB : BB1(), Runnable {
    override fun run() {
        println("BB  run...")
    }

}

/**
 * 泛型测试
 */
fun genericTest() {
    val genericTest = GenericTest<AA>()
    genericTest.execute(AA())

    val genericTest2 = GenericTest<BB>()
    genericTest2.execute(BB())
}
