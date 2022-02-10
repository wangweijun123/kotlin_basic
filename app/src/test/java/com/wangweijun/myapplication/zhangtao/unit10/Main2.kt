package com.wangweijun.myapplication.zhangtao.unit10

import org.junit.Test

class Main2 {

    open class Animal {
         open fun eat() {
             println("Animal eat")
         }
    }

    class Cat : Animal() {
        override fun eat() {
            println("Cat eat")
        }
    }

    class Dog : Animal() {
        override fun eat() {
            println("dog eat")
        }
    }

    @Test
    fun main0() {
        val animals = mutableListOf<Animal>()
        animals.add(Dog())
        animals.add(Cat())
        println(animals.size)
        animals.forEach {
            println(it.eat())
        }
    }

    @Test
    fun main() {
// 需要MutableList<Animal>，实际传MutableList<Cat> 编译出错
        var list = mutableListOf(Cat())
//        foo(list)
        // 实际上，编译器在这里就会提示错误，我们现在假设编译器不阻止我们，会出什么问题
    }


// 需要父类集合，传入子类集合,编译出错
    private fun foo(list: MutableList<Animal>) {
        // 出错，Cat集合不能存Dog对象
        list.add(Dog())
        // 通过
        val animal: Animal = list[0] // 取出的Cat对象
    }



    @Test
    fun main2() {
        // 需要MutableList<Cat>，实际传MutableList<Animal>,编译出错
        var list = mutableListOf(Animal())
//        foo2(list)
        // 实际上，编译器在这里就会提示错误，我们现在假设编译器不阻止我们，
    // 会出什么问题
    }

    // 需要子类集合，传入父类集合

    fun foo2(list: MutableList<Cat>) {
        // 通过
        list.add(Cat())
//        list.add(Animal())// 出错
        // 出错
        val cat: Cat = list[0] // 实际取出来的是Animal对象
    }

}