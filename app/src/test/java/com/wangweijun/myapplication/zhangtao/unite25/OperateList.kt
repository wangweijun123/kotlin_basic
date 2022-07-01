package com.wangweijun.myapplication.zhangtao.unite25

import org.junit.Test

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/05 3:58 下午
 * version: 1.0
 * desc   : 集合api,就是对集合中数据进行处理: 过滤、转换、分组、分割、求和。
 */
class OperateList {
    @Test
    fun testNewList() {
        val result1 = class1.filter { it.score < 60 }
        val result2 = class1.filter { it.score < 60 }
        println("class1 = $class1")
        println("result1 = $result1")
        println("result2 = $result2")
        println("result1 === result2 ? ${ result1 ===  result2}")
        println("result1[0] === result2[0] ? ${result1[0] === result2[0]}")
        println("result1[0] === class1[2] ? ${result1[0] === class1[2]}")
        println("result1[0].hashCode() === class1[2].hashCode() ? ${result1[0].hashCode() == class1[2].hashCode()}")

    }

    @Test
    fun filterNotPass() {
        val result = class1.filter { it.score < 60 }
        println(result)
    }
/*
[Student(name=小李, score=50), Student(name=小强, score=57)]
*/

    @Test
    fun mapName() {
        val result =
            class1.filter { it.score < 60 }
            .map { it.copy(name = "小某某") }
        println(result)
    }

    @Test
    fun mapName2() {
        val result =class1.map { it.copy(name = "小某某") }
        println("class1 = $class1")
        println("result = $result")
    }

    @Test
    fun mapName3() {
        // build error name is val
        /*val result =class1.map { it.name = "小某某" }
        println("class1 = $class1")
        println("result = $result")*/
    }

    @Test
    fun testFlatten() {
        val list = listOf(listOf(1, 2, 3), listOf(4, 5, 6,7))
        println(list)
        val result = list.flatten()
        println(result)
    }

    /**
     *  [[1, 2, 3], [4, 5, 6, 7]]
        [1, 2, 3, 4, 5, 6, 7]
     */

    @Test
    fun groupStudent() {
        val result = class1.groupBy { "${it.score / 10}0分组" }
        println(result)
    }

/*
{
80分组=[Student(name=小明, score=83), Student(name=小林, score=86)],
90分组=[Student(name=小红, score=92), Student(name=小刚, score=97)],
50分组=[Student(name=小李, score=50), Student(name=小强, score=57)],
60分组=[Student(name=小白, score=67)],
70分组=[Student(name=小琳, score=72)]}
*/

    @Test
    fun takeStudent() {
        val first3 = class1
            .sortedByDescending { it.score }
            .take(3)

        val last3 = class1
            .sortedByDescending { it.score }
            .takeLast(3)

        println(first3)
        println(last3)
    }

/*
[Student(name=小刚, score=97), Student(name=小红, score=92), Student(name=小林, score=86)]
[Student(name=小白, score=67), Student(name=小强, score=57), Student(name=小李, score=50)]
*/

    @Test
    fun dropStudent() {
        val middle = class1
            .sortedByDescending { it.score }
            .drop(3)
            .dropLast(3)
        // 剔除前三名、后三名，剩余的学生
        println("class1 = $class1")
        println("middle = $middle")
    }

/*
[Student(name=小明, score=83), Student(name=小琳, score=72)]
*/

    @Test
    fun sliceStudent() {
        val first3 = class1
            .sortedByDescending { it.score }
            .slice(0..2)

        val size = class1.size

        val last3 = class1
            .sortedByDescending { it.score }
            .slice(size - 3 until size)

        println(first3)
        println(last3)
    }
/*
[Student(name=小刚, score=97), Student(name=小红, score=92), Student(name=小林, score=86)]
[Student(name=小白, score=67), Student(name=小强, score=57), Student(name=小李, score=50)]
*/

    @Test
    fun sumScore() {
        val sum1 = class1.sumOf { it.score }

        val sum2 = class1
            .map { it.score }
            .reduce { acc, score -> acc + score }

        val sum3 = class1
            .map { it.score }
            .fold(0) { acc, score -> acc + score }

        println(sum1)
        println(sum2)
        println(sum3)
    }

    @Test
    fun testListFold() {
        val list = class3.map {
            it.score
        }
        println(list)

        val result = list.fold(0) { initial, score ->
            initial + score
        }
        println()
    }



/*
604
604
604
*/

    data class Student(
        val name: String = "",
        val score: Int = 0
    )

    val class1: List<OperateList.Student> = listOf(
        Student("小明", 83),
        Student("小红", 92),
        Student("小李", 50),
        Student("小白", 67),
        Student("小琳", 72),
        Student("小刚", 97),
        Student("小强", 57),
        Student("小林", 86)
    )

    val class2 = listOf(
        Student("大明", 80),
        Student("大红", 97),
        Student("大李", 53),
        Student("大白", 64),
        Student("大琳", 76),
        Student("大刚", 92),
        Student("大强", 58),
        Student("大林", 88)
    )

    val class3 = listOf(
        Student("大明", 10),
        Student("大红", 30),
        Student("大李", 60),
    )

}