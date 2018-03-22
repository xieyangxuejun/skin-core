package com.foretree.skincore

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.foretree.skin.SkinManager


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun changeSkin(view: View) {
        SkinManager.loadSkin(filesDir.absolutePath + "/skin/skin.apk")
    }

    fun reset(view: View) {
        SkinManager.loadSkin(null)
    }
}
