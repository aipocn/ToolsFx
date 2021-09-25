package me.leon.classical

import me.leon.ext.sliceList

fun String.autoKey(keyword: String): String {
    val key = keyword.uppercase()
    val stripText = this.replace("\\s".toRegex(), "")
    val splits = split("\\s+".toRegex()).map { it.length }.also { println(it) }
    return stripText
        .virgeneneEncode(key + stripText.also { println(it) })
        .also { println(it) }
        .sliceList(splits)
}

fun String.autoKeyDecrypt(keyword: String): String {
    val key = keyword.uppercase()
    val splits = split("\\s".toRegex()).map { it.length }.also { println(it) }
    val stripText = this.replace("\\s".toRegex(), "")
    val keyBuilder = StringBuilder(stripText.length + key.length)
    keyBuilder.append(key)
    while (keyBuilder.length < stripText.length + key.length) {
        val substring =
            stripText
                .virgeneneDecode(keyBuilder.toString(), keyBuilder.length)
                .substring(
                    keyBuilder.length - key.length,
                    keyBuilder.length.takeIf { it < stripText.length } ?: stripText.length
                )
        keyBuilder.append(substring)
    }

    return keyBuilder.toString().replaceFirst(keyword, "").sliceList(splits)
}
