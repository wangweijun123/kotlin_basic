package com.wangweijun.myapplication.zhangtao

import org.junit.Test

/**
 * 类 接口，继承， 嵌套， 数据类， 密封类, 枚举
 */
class ZT02 {

    @Test
    fun testClass() {
        val person = Person("dx", 18)
        println(person.isAdult())

        val person2 = Person2("dx", 18)
        println(person2.isAdult)
    }

    @Test
    fun testClass2() {
        val person = Person3("dx")
//        person.age = 18  // 如果age的setter函数编程私有会报错
        println(person.age)
    }

    /**
     * kotlin 自动为var变量生产getter，setter方法
     * val 生成getter方法
     */
    class Person(val name: String, var age: Int) {
        fun isAdult() = age >= 18


    }

    class Person2(val name: String, var age: Int) {
        // 清注意，这里还是一个方法
        val isAdult: Boolean
            get() {
                // do something else
                return age >= 18
            }
    }

    class Person3(val name: String) {
        var age: Int = 0
            //  这就是age属性的setter方法，自定义他
            private set(value) {
                println("set value $value")
                field = value
            }
            get() {
                println("get vale $field")
                return field
            }

    }

    @Test
    fun testClassExtend() {
        val boy = MyBoy()
        println(boy.canWalk)
        println(boy.walk2())
    }

    open class MyPerson() {
        open val canWalk: Boolean = false
        val age: Int = 18
        fun walk() {}

        open fun walk2() {
            println("parent walk2")
        }
    }

    class MyBoy : MyPerson() {
        // 子类复写父类成员，第一需要父类打开，第二加上override
        override val canWalk: Boolean = true

        override fun walk2() {
            super.walk2()
            println("boy walk2")
        }
    }

    abstract class AbsPerson(open val name: String) {
        abstract fun walk()
        // 省略
    }

    class Boy(override val name: String) : AbsPerson(name) {
        override fun walk() {
            TODO("Not yet implemented")
        }

    }

    /**
     *
     */
    interface Behavior {
        // 接口内可以有属性
        var canWalk: Boolean

        // 接口有默认实现
        fun walk() {
            if (canWalk) {
                // do something
                println("i can walk")
            } else {
                println("i can not walk")
            }
        }

        fun walk2();
    }

    class BehaviorImpl : Behavior {
        // 重写接口的属性
        override var canWalk: Boolean = false
            get() {
                return field
            }

        override fun walk2() {
            println(" has not impl")
        }
    }

    class BehaviorImpl2 : Behavior {
        // 重写接口的属性
        override var canWalk: Boolean = false
            get() {
                return field
            }

        override fun walk2() {
            println(" has not impl")
        }
    }

    class BehaviorImpl3 : Behavior {
        // 重写接口的属性
        override var canWalk: Boolean = false
            get() {
                return field
            }

        override fun walk2() {
            println(" has not impl")
        }
    }

    @Test
    fun testInterface() {
        val behaviorImpl = BehaviorImpl()
        behaviorImpl.canWalk = true
        behaviorImpl.walk()
        testInterface(behaviorImpl)
    }

    fun testInterface(behavior: Behavior) {
        when (behavior) { // when 对于自己写的实现类没法自动判断是否完备, else 也不用写,很危险
            is BehaviorImpl -> println("BehaviorImpl")
            is BehaviorImpl2 -> println("BehaviorImpl2")
        }
    }

    /**
     * 嵌套类, 相当于java中 静态的内部类
     */
    class A {
        val nameXXX: String = "dx"
        fun foo() = 1

        class B {
            // 静态的内部类无法访问外部类的成员
//            val a = nameXXX // build error
//            int b = foo() // build error
        }
    }

    class AA {
        val nameXXX: String = "dx"
        fun foo() = 1
        // inner 关键字表明是内部类
        inner class BB {
            val a = nameXXX
            val b = foo()
        }
    }

    @Test
    fun testInnerClass() {

    }



    // 数据类 (最少要有一个属性)
    data class PersonData(val name: String, val age: Int)
    @Test
    fun testDataClass() {
        val tom = PersonData("Tom", 18)
        val jack = PersonData("Jack", 19)
        println(tom.equals(jack)) // 输出：false
        println(tom.hashCode())   // 输出：对应的hash code
        println(tom.toString())   // 输出：Person(name=Tom, age=18)

        val (name, age) = tom     // name=Tom, age=18
        println("name is $name, age is $age .")

        val mike = tom.copy(name = "Mike") // 创建新对象
        println(mike)             // 输出：Person(name=Mike, age=18)
        println("tom.hashCode() = ${tom.hashCode()}, mike.hashCode() = ${mike.hashCode()}")
    }

    // 密封类，是更强大的枚举类

    // 枚举类

    enum class Human {
        MAN, WOMAN
    }

    fun isMan(data: Human) = when (data) {
        Human.MAN -> true
        Human.WOMAN -> false // 注释掉不行，必须完备
        // 这里不需要else分支，编译器自动推导出逻辑已完备
    }

    @Test
    fun testEnumClass() {
        println(isMan(Human.MAN))
        // 枚举也有它的局限性
        // 说明 每一个枚举的值，它在内存当中始终都是同一个对象引用
        println(Human.MAN == Human.MAN) // true
        println(Human.MAN === Human.MAN) // true

    }

    @Test
    fun testEnumClassValueOf() {
        // valueOf 特别注意，传进去的只能是枚举字符串，否则抛出异常
        val valueOf = Human.valueOf("MAN")
        println(valueOf == Human.MAN) // true
        assert(valueOf == Human.MAN)
    }

    @Test
    fun testSealed() {
        val suc = MyRes.Suc()
        val suc2 = MyRes.Suc()
        println("suc === suc2 ? ${suc === suc2}")
        testRes(suc)
    }

    fun testRes(myRes: MyRes) {
        when (myRes) {
            is MyRes.Suc -> "xxx"
        }
    }

    sealed class MyRes {
        class Suc : MyRes()
        class Error : MyRes()
    }

    // 密封类
    sealed class Result<out R> {
        data class Success<out T>(val data: T, val message: String = "") : Result<T>()

        data class Error(val exception: Exception) : Result<Nothing>()

        data class Loading(val time: Long = System.currentTimeMillis()) : Result<Nothing>()
    }

//    fun display(data: Result) = when(data) {
//        Result.Success -> displaySuccessUI(data)
//        Result.Error -> showErrorMsg(data)
//        Result.Loading -> showLoading()
//    }

    @Test
    fun testBoolean() {
        val flag: Boolean = true // kotlin 中没有原始类型，一切是对象，java有原始类型哦,
        if (flag) {
            println(" is true")
        } else {
            println(" is false")
        }
        if (flag == true) {

        }
    }
}

