package com.wangweijun.myapplication

// open 与 final 相反, kotlin的class默认背编译成final class
open class Animal
class Dog : Animal()

// 对kotlin (java)的类进行方法添加或者说扩展，
fun Animal.name() = "animal"
fun Dog.name() = "Dog"

// 这里会被编译成如下
// public static final printName(Lcom/wangweijun/myapplication/Animal;Lcom/wangweijun/myapplication/Animal;)V
fun Animal.printName(anima: Animal) {
    println(anima.name())
}