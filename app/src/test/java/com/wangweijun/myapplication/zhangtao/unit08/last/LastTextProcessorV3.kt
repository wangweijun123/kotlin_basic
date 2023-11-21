package com.wangweijun.myapplication.zhangtao.unit08.last

import com.wangweijun.myapplication.zhangtao.unit08.WordFreq
import org.junit.Test

class LastTextProcessorV3 {

//    1 清洗文本 2 分割文本 3 统计词频 4 排序

    @Test
    fun test() {
        val text = "Kotlin is my favorite language. I love Kotlin! is is"
        processText(text)
    }

    private fun processText(text: String) {
        // 1 清洗文本
        // 2 分割文本
        val sortedBy = text
            .clean()
            .split(" ")
            .getWordCount()
            .mapToList{ WordFreq(it.key, it.value) }
            .sortedByDescending { it.frequency }
        // 4 排序
        println("sortedBy = $sortedBy")
    }


    private inline fun <T> HashMap<String, Int>.mapToList(transform: (Map.Entry<String, Int>) -> T): List<T> {
        val list = mutableListOf<T>()
        for (entry in this) {
            list.add(transform(entry))
        }
        return  list
    }

    private fun List<String>.getWordCount(): HashMap<String, Int> {
        val map = hashMapOf<String, Int>()
        for (item in this) {
            if (item.trim().isEmpty()) continue
            var count = map.getOrDefault(item, 0)
            map[item] = ++count
        }
        return map
    }

    private fun String.clean() = this.replace("[^A-Za-z]".toRegex(), " ")

    @Test
    fun testList() {
        val intRange: IntRange = 1..10
        println(reduce())
        println(sum())

    }

    fun reduce() = (1..10).reduce { acc, i -> acc + i } // 结果 55
    fun sum() = (1..10).sum() // 结果 55
}