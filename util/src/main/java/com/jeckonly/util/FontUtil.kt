package com.jeckonly.util

import android.content.Context
import android.graphics.Typeface

object FontUtil {

    /**
     * 设置：findViewById<TextView>(R.id.textView).typeface = FontUtil.getTypeface(requireContext())
     */
    fun getTypeface(context: Context, typefaceStr: String = "DOUYUFONT-2.OTF") =
        Typeface.createFromAsset(context.assets, typefaceStr)

}