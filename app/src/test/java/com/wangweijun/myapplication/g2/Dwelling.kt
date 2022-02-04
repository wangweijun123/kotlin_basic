package com.wangweijun.myapplication.g2

import org.junit.Test
import kotlin.math.PI

/**
 * 创建一个类层次结构，这是一种包含类的树形结构，其中子级会继承父类的函数。子类继承的有属性和函数。
创建一个 abstract 类，在这种类中，部分函数会留给其子类来实现。因此，abstract 类无法被实例化。
创建 abstract 类的子类。
使用 override 关键字，在子类中替换属性和函数。
使用 super 关键字，引用父类中的函数和属性。
将一个类标记为 open，使其能够被子类化。
将一个属性标记为 private，使其只能在相应类中使用。
使用 with 构造函数，在同一对象实例上进行多次调用。
从 kotlin.math 库导入函数
 *
 *
 * 住房
 * 人数 : private var residents, 第一他是变量: 住房的人数是可以变化的, 第二 私有属性,如果不加修饰符,默认成员都是编译成public
 */
abstract class Dwelling(private var residents: Int) {
    // 不可变变量: 住房的材料一旦赋值是不会变的
    abstract val buildingMaterial: String
    // 不指定可见性修饰符，相应属性和方法会默认为 public
    abstract val capacity: Int

    abstract fun floorAre(): Double

    fun hasRomm(): Boolean {
        return residents < capacity
    }

    fun getRomm() {
        if (capacity > residents) {
            residents ++
            println("You got a room!")
        } else {
            println("Sorry, at capacity and no rooms left.")
        }
    }
}

/**
 * 不要将 residents 声明为 val,，因为您是在重复使用已在父类 Dwelling 中声明的属性。
 */
class SquareCabin(override val buildingMaterial: String, override val capacity: Int,
                  residents: Int) : Dwelling(residents) {
    override fun floorAre(): Double {
        TODO("Not yet implemented")
    }

}

/**
 * 第二版本
 */
class SquareCabin2(residents: Int,
     private val length: Double) : Dwelling(residents) {
    // override 关键词表示此变量是父类定义的,并且在此类中将被替换掉。
    override val buildingMaterial: String = "wood"
    override val capacity: Int = 10

    override fun floorAre(): Double {
        return length * length
    }
}

open class RoundHut(residents: Int,
    val radius: Double) : Dwelling(residents) {
    override val buildingMaterial = "Straw"
    override val capacity = 4

    override fun floorAre(): Double {
        return PI * radius * radius
    }
}

class RoundTower(
    residents: Int,
    radius: Double,
    private val floors: Int = 10) : RoundHut(residents, radius) {
    override val buildingMaterial: String = "Stone"
    override val capacity: Int = 4 * floors

    override fun floorAre(): Double {
        return super.floorAre() * floors
    }
}

class Main {
    @Test
    fun test() {
        val squareCabin = SquareCabin2(6, 5.0)
        println("capacity = ${squareCabin.capacity}")
        println("buildingMaterial = ${squareCabin.buildingMaterial}")
        println("hasRomm ? ${squareCabin.hasRomm()}")

        with(squareCabin) {
            println(this)
            println("capacity = ${this.capacity}" )
            println("capacity = $capacity" )
            println("area = ${floorAre()}")
            println("squareCabin === this ? " + (squareCabin === this))
        }

        val roundHut = RoundHut(3, 10.9)
        with(roundHut) {
            println(this)
            println("capacity = ${this.capacity}" )
            println("hasRomm ? ${hasRomm()}" )
            println("area = ${floorAre()}")
        }

        val roundTower = RoundTower(4, 2.5)
        with(roundTower) {
            println("\nRound Tower\n==========")
            println("Material: ${buildingMaterial}")
            println("Capacity: ${capacity}")
            println("Has room? ${hasRomm()}")
            println("area = ${floorAre()}")
            // 仅显示 2 位小数
            println("area = %.2f".format(floorAre()))
        }
    }
}