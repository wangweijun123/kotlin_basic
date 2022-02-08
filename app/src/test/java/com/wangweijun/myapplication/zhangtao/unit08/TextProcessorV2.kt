package com.wangweijun.myapplication.zhangtao.unit08

import org.junit.Test


class TextProcessorV2 {
    @Test
    fun main() {
        val text = "Kotlin is my favorite language. I love Kotlin Kotlin Kotlin!"
        val list = processText(text)
        for (item in list) {
            println(item)
        }
    }

    @Test
    fun main2() {
        val text = "Kotlin is my favorite language. I love Kotlin Kotlin Kotlin!"
        val list = processText2(text)
        for (item in list) {
            println(item)
        }
    }
    // Kotlin 库函数
    private fun processText2(text: String): List<WordFreq> {
        return text
            .clean()
            .split(" ")
            .filter { it != "" }
            .groupBy { it }
            .map { WordFreq(it.key, it.value.size) }
            .sortedByDescending { it.frequency }
    }

    // 函数式编程的思想
   private fun processText(text: String): List<WordFreq> {
        return text
            .clean()
            .split(" ")
            .getWordCount()
            .mapToList { WordFreq(it.key, it.value) }
            .sortedByDescending {
                it.frequency
            }
    }

    private fun String.clean(): String {
        return this.replace("[^A-Za-z]".toRegex(), " ")
    }

    private fun List<String>.getWordCount(): Map<String, Int> {
        val map = hashMapOf<String, Int>()
        for (word in this) {
            if (word == "") continue
            val trim = word.trim()
            val count = map.getOrDefault(trim, 0)
            map[trim] = count + 1
        }
        return map
    }

    // 增加inline关键字
    // inline 1 修饰高阶函数 2 将inline函数的代码copy调用处
    private inline fun <T> Map<String, Int>.mapToList(transform: (Map.Entry<String, Int>) -> T): MutableList<T> {
        val list = mutableListOf<T>()
        for (entry in this) {
            val freq = transform(entry)
            list.add(freq)
        }
        return list
    }


    private fun Map<String, Int>.sortByFrequency2(): MutableList<WordFreq> {
        val list = mutableListOf<WordFreq>()
        for (entry in this) {
            if (entry.key == "") continue
            list.add(WordFreq(entry.key, entry.value))
        }
        list.sortByDescending {
            it.frequency
        }
        return list
    }
}