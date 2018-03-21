package com.foretree.skin

import android.graphics.drawable.Drawable

data class SkinPair(var attributeName: String, var resId: Int)
data class SkinDrawable(var dLeft: Drawable?, var dRight: Drawable?, var dTop: Drawable?, var dBottom: Drawable?)