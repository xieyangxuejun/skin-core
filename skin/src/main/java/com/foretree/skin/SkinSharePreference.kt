package com.foretree.skin

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by silen on 21/03/2018.
 */
class SkinSharePreference private constructor(context: Context) {
    private val NAME_SKIN = "skin"
    private val KEY_SKIN_PATH = "skin_path"
    private var mSpf: SharedPreferences = context.getSharedPreferences(NAME_SKIN, Context.MODE_PRIVATE)

    companion object {
        private lateinit var mInstance: SkinSharePreference

        fun getInstance(context: Context): SkinSharePreference {
            if (mInstance == null)
                mInstance = SkinSharePreference(context)
            return mInstance
        }
    }

    fun setSkin(path: String) {
        mSpf.edit().putString(KEY_SKIN_PATH, path).apply()
    }

    fun getSkin(): String {
        return mSpf.getString(KEY_SKIN_PATH, "")
    }

}