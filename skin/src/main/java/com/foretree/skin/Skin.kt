package com.foretree.skin

import java.io.File


/**
 * Created by silen on 22/03/2018.
 */

class Skin(var md5: String = "", var name: String = "", var url: String = "") {

    /**
     * 下载完成后缓存地址
     */
    var path = ""

    lateinit var file: File

    fun getSkinFile(theme: File): File {
        file = File(theme, name)
        path = file.absolutePath
        return file
    }
}