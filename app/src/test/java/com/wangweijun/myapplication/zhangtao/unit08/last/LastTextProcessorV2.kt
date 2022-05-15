package com.wangweijun.myapplication.zhangtao.unit08.last

import com.wangweijun.myapplication.zhangtao.unit08.WordFreq
import org.junit.Test
import java.io.File

class LastTextProcessorV2 {
    @Test
    fun test() {
        val text = "Kotlin is my favorite language. I love Kotlin!"
        println(processText(text))
    }

    private fun processText(text: String): List<WordFreq> {
        return text.clean()
                    .split(" ")
                    .getWordCount()
                    .mapToList{ WordFreq(it.key, it.value) }
                    .sortedByDescending {
                        it.frequency
                    }

    }

    private fun List<String>.getWordCount(): HashMap<String, Int> {
        val map = hashMapOf<String, Int>()
        for (item in this) {
            if (item.trim().isEmpty()) continue
            val count = map.getOrDefault(item.trim(), 0)
            map[item.trim()] = count+1
        }
        return map
    }

    private fun String.clean(): String {
        return this.replace("[^A-Za-z]".toRegex(), " ")
    }

    private inline fun <T> Map<String, Int>.mapToList(transfom : (Map.Entry<String, Int>) -> T): List<T> {
        val list = mutableListOf<T>()
        for (entry in this) {
            if (entry.key.trim().isEmpty()) continue
            list.add(transfom(entry))
        }

        return list
    }

    @Test
    fun test2() {
        val text = "Kotlin is my favorite language. I love Kotlin!"
        println(processText2(text))
    }
    private fun processText2(text: String): List<WordFreq> {
        return text
            .clean()
            .split(" ")
            .filter { it != "" }
            .groupBy {
                it
            }
            .map {
                WordFreq(it.key, it.value.size)
            }
            .sortedByDescending { it.frequency }
    }
}