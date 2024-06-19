package com.melvin.compass.main.domain


fun String.generateTenthString(): String {
    val result = StringBuilder()
    for (i in 9 until this.length step 10) {
        result.append(this[i])
    }
    return result.toString()
}

fun String.countWordOccurrences(): List<WordCount> {
    val words = this.split("\\s+".toRegex())
    val wordCountMap = mutableMapOf<String, Int>()

    for (word in words) {
        val count = wordCountMap.getOrDefault(word, 0)
        wordCountMap[word] = count + 1
    }

    val wordCountList = mutableListOf<WordCount>()
    for ((word, count) in wordCountMap) {
        wordCountList.add(WordCount(word, count))
    }

    return wordCountList
}
