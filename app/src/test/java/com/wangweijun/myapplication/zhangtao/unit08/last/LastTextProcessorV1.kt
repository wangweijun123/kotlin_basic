package com.wangweijun.myapplication.zhangtao.unit08.last

import com.wangweijun.myapplication.zhangtao.unit08.WordFreq
import org.junit.Test
import java.io.File

class LastTextProcessorV1 {
    @Test
    fun test() {
        val text = "Kotlin is my favorite language. I my love Kotlin!"
        processText(text)
    }

    fun processText(text: String) {
        println(text)
        // 1 清洗文本
        val clean = clean(text)
        println(clean)
        val words = clean.split(" ")
        println(words.size)
        println(words)
        // 步骤3
        val map = getWordCount(words)
        println(map)
        // 排序
        val sortList = sortByFrequency(map)
        println(sortList)
    }

    private fun getWordCount(words: List<String>): HashMap<String, Int> {
        val map = hashMapOf<String, Int>()
        for (item in words) {
            if (item.trim().isEmpty()) continue
            val count = map.getOrDefault(item.trim(), 0)
            map.put(item.trim(), count+1)
        }
        return map
    }

    private fun clean(text: String): String {
        return text.replace("[^A-Za-z]".toRegex(), " ")
    }

    private fun sortByFrequency(map: Map<String, Int>): List<WordFreq> {
        val list = mutableListOf<WordFreq>()
        for (entry in map) {
            if (entry.key.trim().isEmpty()) continue
            list.add(WordFreq(entry.key, entry.value))
        }
        list.sortByDescending {
            it.frequency
        }
        return list
    }


    fun processFile(file: File) {
        val text = file.readText(Charsets.UTF_8)
        processText(text)
    }
}