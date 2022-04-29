package com.wangweijun.myapplication.g3

import com.wangweijun.myapplication.mycoroutines.launchTest
import com.wangweijun.myapplication.mycoroutines.testGlobalScope
import org.junit.Test

class ListDemo {

}

class Main {

    @Test
    fun testList() {
        /**
         * 一个class的功能的,不只是class本身的功能,还有更加nb的就是对class的扩展
         */
        val numbers = listOf(1, 3, 5, 7)
        println("List: $numbers")
        println("List: ${numbers[1]}")
        println("List: ${numbers.last()}")
        println("${numbers.contains(5)}")

//        numbers = listOf(1, 3, 5, 7) // build error



//        numbers[0] = 2
    }

    @Test
    fun testMutableList() {
        val entrees = mutableListOf<String>()
        println("Entrees: $entrees")
        println("Add noodles: ${entrees.add("noodles")}")
        println("Entrees: $entrees")
        println(entrees.contains("noodles"))
    }

    @Test
    fun testMutableList3() {
        // val 定义的变量不能再指向别的变量，是这么个意思
        var entrees = mutableListOf<String>()
        println("Entrees: $entrees")
        println("Add noodles: ${entrees.add("noodles")}")
        println("Entrees: $entrees")
        println(entrees.contains("noodles"))

        entrees = mutableListOf<String>()
    }

    @Test
    fun testMutableList2() {
        val entrees = mutableListOf<String>()
        println("Entrees: $entrees")

        // Add individual items using add()
        println("Add noodles: ${entrees.add("noodles")}")
        println("Entrees: $entrees")
        println("Add spaghetti: ${entrees.add("spaghetti")}")
        println("Entrees: $entrees")

        // Add a list of items using addAll()
        val moreItems = listOf("ravioli", "lasagna", "fettuccine")
        println("Add list: ${entrees.addAll(moreItems)}")
        println("Entrees: $entrees")

        // Remove an item using remove()
        println("Remove spaghetti: ${entrees.remove("spaghetti")}")
        println("Entrees: $entrees")
        println("Remove item that doesn't exist: ${entrees.remove("rice")}")
        println("Entrees: $entrees")

        // Remove an item using removeAt() with an index
        println("Remove first element: ${entrees.removeAt(0)}")
        println("Entrees: $entrees")

        // Clear out the list
        entrees.clear()
        println("Entrees: $entrees")

        // Check if the list is empty
        println("Empty? ${entrees.isEmpty()}")
    }

    @Test
    fun accessList() {
        val guestsPerFamily = listOf(2, 4, 1, 3)
        var total = 0
        var currentIndex = 0
        while (currentIndex < guestsPerFamily.size) {
            total += guestsPerFamily[currentIndex]
            currentIndex++
        }
        println("total =  $total")

        var total2 = 0
        // for in
        for (num in guestsPerFamily) {
            total2 += num
        }
        println("total2 =  $total2")

        for ((index, item) in guestsPerFamily.withIndex()) {
            println("第$index 个元素 值为$item #")
        }

        /**
         *
        for (item in list) print(item) // Iterate over items in a list

        for (item in 'b'..'g') print(item) // Range of characters in an alphabet

        for (item in 1..5) print(item) // Range of numbers

        for (item in 5 downTo 1) print(item) // Going backward

        for (item in 3..6 step 2) print(item) // Prints: 35
         *
         */

    }

    @Test
    fun testVararg () {
        xxxx("a", "b", "c")
    }

    /**
     * 注意：只能将一个参数标记为 vararg，通常是列表中的最后一个参数
     * 可变的同类型参数
     */
    fun xxxx(vararg params: String) {
        println(params)
        for ((myIndex, myValue) in params.withIndex()) {
            println("myIndex = $myIndex , myValue=$myValue")
        }
    }

    @Test
    fun testFood() {
        val nolldles = Nolldles()
//        val vegetables = Vegetables("a", "b", "c", "d")
        val vegetables = Vegetables()
        println(nolldles)
        println(vegetables)
    }

    @Test
    fun testFood2() {
        val nolldles = Nolldles()
//        val vegetables = Vegetables("a", "b", "c", "d")
        val vegetables = Vegetables()
        val order = Order(1)
        order.addItem(Nolldles())
        order.addItem(Nolldles())
        order.addItem(Nolldles()).addItem(Nolldles())
        order.print()
    }

    @Test
    fun testFood3() {
        val order1 = Order(1)
        order1.addItem(Nolldles())
        order1.print()

        println()

        val order2 = Order(2)
        order2.addItem(Nolldles())
        order2.addItem(Vegetables())
        order2.print()

        println()

        val order3 = Order(3)
        val items = listOf(Nolldles(), Vegetables("Carrots", "Beans", "Celery"))
        order3.addAll(items)
        order3.print()
    }

    @Test
    fun testFoodxxx() {
        // 如果getItem2()返回null, 执行后面Item2("dx", 18), 如果不为NULL
        // 都会执行 apply 操作,也就是说执行apply操作确保不为null
        val item2 = (getItem2() ?: Item2("dx", 18)).apply {
            // 直接使用对象的成员
            println("before update price $price")
            price = 200
        }
    }

    @Test
    fun testFoodxxx22() {
//        launchTest()
//        testGlobalScope()
        paramsCanNull()
    }


    fun getItem2(): Item2? {
//        return Item2("wang", 10) // before update price 10
        return null // before update price 18
    }
    // lambda 最后一行语句是返回值

    /**
     * param has default value  can
     */
    fun paramsCanNull(name: String? = null) {
        println(name)
    }

    @Test
    fun testReturnObj() {
        access {
            println(it)
        }
    }

    fun access(action: (Item2) -> Unit) {
        val item2 = Item2("wwj", 2)
        println(item2)
        action(item2)
    }

    @Test
    fun testWenhaoAnd() {
        println(changeUrl("duanxia")) // duanxia
        println(changeUrl(null)) // default
    }

    private fun changeUrl(url: String?): String =
        url?.let {
            url
        } ?: "default"
}

class DataBlock<T> {

}

open class Item2(var name: String, var price:Int)

open class Item(val name: String, val price:Int)

/**
 * 面条
 */
class Nolldles : Item("Nolldles", 10)

/**
 * 蔬菜
 */
class Vegetables(private vararg val toppings: String)  : Item("Vegetables ", 5) {
    override fun toString(): String {
        println("toppings = $toppings")
        return toppings.joinToString("###")
    }
}

class Order(val orderNum: Int) {
    // val 不可变: 不能重新指向
    private val itemList = mutableListOf<Item>()

    // build error, 编译器会自动生产的
    /*fun getOrderNum(): Int {
        return orderNum
    }*/

    /**
     * 形参是不能加 var val的
     *
     */
    fun addItem(newItem: Item): Order {
        itemList.add(newItem)
        return this
    }

    fun addAll(newItems: List<Item>): Order {
        itemList.addAll(newItems)
        return this
    }

    fun print() {
        var total = 0
        for (item in itemList) {
            total += item.price
        }
        println("total = $total")
    }
}


