package com.wangweijun.myapplication.zhangtao.unit08

import org.junit.Test

data class WordFreq(val word: String, val frequency: Int)

class TextProcessorV1 {
    @Test
    fun main() {
        val text = "Kotlin is my favorite language. I love Kotlin!"
        val list = processText(text)
        for (item in list) {
            println(item)
        }
    }

    fun processText(text: String): List<WordFreq> {
        val cleaned = clean(text)
        val words = cleaned.split(" ")
        val map = getWordCount(words)
        val list = sortByFrequency(map)
        return list
    }

    fun clean(text: String): String {
        return text.replace("[^A-Za-z]".toRegex(), " ")
    }

    private fun getWordCount(words: List<String>): Map<String, Int> {
        val map = hashMapOf<String, Int>()
        for (word in words) {
            if (word == "") continue
            val trim = word.trim()
            val count = map.getOrDefault(trim, 0)
            map[trim] = count + 1
        }
        return map
    }

    private fun sortByFrequency(map: Map<String, Int>): List<WordFreq> {
        val list = mutableListOf<WordFreq>()
        for (entry in map) {
            if (entry.key == "") continue
            list.add(WordFreq(entry.key, entry.value))
        }
        list.sortByDescending {
            it.frequency
        }
        return list
    }
}