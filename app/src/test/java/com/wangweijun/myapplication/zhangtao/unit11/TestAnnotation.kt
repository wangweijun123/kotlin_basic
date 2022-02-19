package com.wangweijun.myapplication.zhangtao.unit11

import org.junit.Test
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.full.memberProperties

// 注解只存在于源代码，编译后不可见 SOURCE,
// 注解编译后可见，运行时不可见 BINARY,
// 编译后可见，运行时可见 RUNTIME
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.BINARY)
annotation class MyDeprecated(
    val message: String,
    val replaceWith: ReplaceWith = ReplaceWith(""),
    val level: DeprecationLevel = DeprecationLevel.ERROR
)

@MyDeprecated(
    "use CalculatorV3",
    ReplaceWith("CalculatorV3"),
    DeprecationLevel.ERROR
)
class Calculator {
    fun add(a: Int, b: Int) = a - b
}

class CalculatorV3 {
    fun add(a: Int, b: Int) = a + b
}

class TestAnnotation {

    @Test
    fun annotationTest() {
        val r = Calculator()
        /*val result = Calculator().add(1, 2)
        println("result = $result")*/
    }

    @Test
    fun reflectTest() {
        val student = DxStudent("tom", 56.0, 22)
        println("modify before student = $student")
//        readMembers(student)
        modifyAddressMember(student)
        println("modify after student = $student")
        /*val result = Calculator().add(1, 2)
        println("result = $result")*/
    }

    private fun readMembers(obj: Any) {
        println("classname = ${obj::class.simpleName}")
        obj::class.memberProperties.forEach {
            println("${it.name} is KMutableProperty1 ? ${it is KMutableProperty1}")
            // 获取属性的名字与属性的value
            println("${it.name} = ${it.getter.call(obj)}") // call 调用对应的方法
        }
    }

    /**
     * 修改属性name的值
     * @param obj Any
     */
    private fun modifyAddressMember(obj: Any) {
        val kClass = obj::class
        println("class = ${obj::class}")
        println("classname = ${obj::class.simpleName}")
        obj::class.memberProperties.forEach {
            if (it is KMutableProperty1) {
                it.setter
            }
            if (it.name == "name"
                && it is KMutableProperty1
                && it.setter.parameters.size == 2 // it.setter is KCallable
                && it.getter.returnType.classifier == String::class
            ) {
                // 调用set方法
                it.setter.call(obj, "dx")
                println("====== name changed ======")
            }
        }
    }
}

data class DxStudent (
    var name: String,
    val score: Double,
    val height: Int
)



