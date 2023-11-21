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
        // 添加dog和cat
        animals.add(Dog())
        animals.add(Cat())

        println(animals.size)
        animals.forEach {
            println(it.eat())
        }
        val animal = animals[0]
    }

    @Test
    fun main() {
// 需要MutableList<Animal>，实际传MutableList<Cat> 编译出错
//        var list = mutableListOf(Cat()) // build error
        var list = mutableListOf<Animal>() // build success
        foo(list)
        // 实际上，编译器在这里就会提示错误，我们现在假设编译器不阻止我们，会出什么问题
    }


// 需要父类集合，传入子类集合,编译出错
    private fun foo(list: MutableList<Animal>) {
        list.add(Dog())
        list.add(Cat())
        // 通过
        val animal = list[0]
    }


    /**
     * 需要什么集合就只能传什么集合
     */
    @Test
    fun main2() {
        // 需要MutableList<Cat>，实际传MutableList<Animal>,编译出错
//        var list = mutableListOf<Animal>(Animal()) // 编译出错
        var list = mutableListOf(Cat())
        foo2(list)
        // 实际上，编译器在这里就会提示错误，我们现在假设编译器不阻止我们，
    // 会出什么问题
    }

    // MutableList<Cat> 与 MutableList<Animal> 有什么关系，没有关系
    // 两者没有任何关系，只是两个不同的集合而已,需要什么传什么
    fun foo2(list: MutableList<Cat>) {
        list.add(Cat())
        val cat: Cat = list[0]
        println(cat.eat())
    }

}