package com.foretree.skincore

import android.app.Application
import android.util.Log
import com.foretree.skin.SkinManager
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception

/**
 * Created by silen on 22/03/2018.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        copyAsset()
        SkinManager.init(this)
    }

    private fun copyAsset() {
        val dirs = filesDir.absolutePath + "/skin"
        val dirFile = File(dirs)
        if (!dirFile.exists()) {
            dirFile.mkdirs()
        }

        val filePath = dirFile.absolutePath + "/skin.apk"

        filePath.let {
            val file = File(it)
            file.deleteOnExit()

            val fIn = assets.open("skin.apk")
            val fOut = FileOutputStream(filePath)
            try {
                var read = 0
                val buffer = ByteArray(10240)
                while (read != -1) {
                    read = fIn.read(buffer)
                    fOut.write(buffer, 0, read)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                fIn.close()
                fOut.flush()
                fOut.close()
            }
        }

        Log.d("skin", "copy success")
    }
}