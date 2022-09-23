package com.jeckonly.util

import android.content.Context
import android.util.DisplayMetrics
import android.view.Display
import android.view.WindowManager
import java.lang.reflect.Method


/**
 * Created by NINGMEI(shicaid) on 2022/2/15.
 * Intent: 像素大小相关
 */
object DensityUtil {

    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     */
    fun dip2px(dpValue: Float,context: Context): Int {
        val scale: Float = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dip(pxValue: Float,context: Context): Int {
        val scale: Float = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     */
    fun px2sp(pxValue: Float,context: Context): Int {
        val fontScale: Float =
            context.resources.displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    fun sp2px(spValue: Float,context: Context): Int {
        val fontScale: Float =
            context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    /**
     * 获取屏幕高度--px (不包含虚拟按键)
     *
     * @return
     */
    fun getScreenHeight(context: Context): Int {
        return context.resources.displayMetrics.heightPixels
    }

    /**
     * 获取屏幕宽度--px
     *
     * @return
     */
    fun getScreenWidth(context: Context): Int {
        return context.resources.displayMetrics.widthPixels
    }

    /**
     * 获取实际屏幕高度--px（包含虚拟按键）
     *
     * @return
     */
    fun getRealScreenHeight(context: Context): Int {
        var dpi = getScreenHeight(context)
        val windowManager =
            context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display: Display = windowManager.defaultDisplay
        val dm = DisplayMetrics()
        val c: Class<*>
        try {
            c = Class.forName("android.view.Display")
            val method: Method = c.getMethod("getRealMetrics", DisplayMetrics::class.java)
            method.invoke(display, dm)
            dpi = dm.heightPixels
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return dpi
    }

    /**
     * 获取虚拟按键高度
     *
     * @return
     */
    fun getVirtuakeyHight(context: Context): Int {
        return getRealScreenHeight(context) - getScreenHeight(context) - getStatusBarHeight(context)
    }

    /**
     * 获取状态栏高度
     *
     * @return 状态栏高度
     */
    fun getStatusBarHeight(context: Context): Int {
        // 获得状态栏高度
        val resourceId: Int = context.getResources()
            .getIdentifier("status_bar_height", "dimen", "android")
        return context.resources.getDimensionPixelSize(resourceId)
    }
    
}