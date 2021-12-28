package com.wangweijun.myapplication

class KotlinA : AInterface {
    override fun putNumber(num: Int) {
        println("this is int : $num")
    }

    // 编译出错, kotlin没有包装类 Integer
   /* override fun putNumber(num: Int?) {
        TODO("Not yet implemented")
    }*/

    /**
     * 在KotlinA class中生成了一个静态的名叫 a 实例, 同时生成了一个get函数
     * field private static final a:Lcom/wangweijun/myapplication/KotlinA;
     *
     * 所以可以通过类名调用 KotlinA.a.putNumber(123)
     */
    companion object {
        val a = KotlinA()
    }
}