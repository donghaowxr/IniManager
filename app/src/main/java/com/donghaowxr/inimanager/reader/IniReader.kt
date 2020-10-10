package com.donghaowxr.inimanager.reader

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.util.*
import kotlin.collections.HashMap

/**
 * @Author: donghao
 * @Date: 2020/10/2 23:41
 * @License: Copyright © 2020 donghaowxr
 * @Description:
 */
class IniReader @Throws(IOException::class) private constructor(fileName: String) {
    var sections = HashMap<String, Properties>()
    @Transient
    var currentSecion: String? = null
    @Transient
    var current: Properties? = null

    companion object {
        /**
         * 解析ini文件内容
         */
        @Throws(IOException::class)
        fun parse(fileName: String): IniReader {
            return IniReader(fileName)
        }
    }

    init {
        val reader = BufferedReader(FileReader(fileName))
        read(reader)
        reader.close()
    }

    /**
     * 读取ini文件
     */
    @Throws(IOException::class)
    private fun read(reader: BufferedReader) {
        var line: String? = null
        while (reader.readLine().also {
                if (it != null) {
                    line = it
                }
            } != null) {
            line?.let { parseLine(it) }
        }
    }

    /**
     * 逐行解析ini配置文件
     */
    private fun parseLine(line: String) {
        var parseLine = line.trim()
        if (parseLine.matches(Regex("\\[.*\\]"))) {
            currentSecion = parseLine.replaceFirst(Regex("\\[(.*)\\]"), "$1")
            current = Properties()
            sections[currentSecion!!] = current!!
        } else {
            if (parseLine.contains("=")) {
                current?.setProperty(parseLine.split("=")[0], parseLine.split("=")[1])
            }
        }
    }

    /**
     * 获取ini文件中的值
     */
    fun getValue(section: String, name: String) : String? {
        return sections[section]?.getProperty(name)
    }

    /**
     * 判断是否存在key标签
     */
    fun containsKey(section: String, key: String): Boolean? {
        return sections[section]?.contains(key)
    }
}