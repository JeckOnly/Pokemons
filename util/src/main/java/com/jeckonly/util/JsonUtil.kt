package com.jeckonly.util

import com.google.gson.Gson
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

object JsonUtil {
    private val gson = Gson()

    fun any2Json(src: Any): String {
        return gson.toJson(src)
    }

    fun <T> json2Any(json: String, classType: Class<T>): T {
        return gson.fromJson(json, classType)
    }

    /**
     * 配合getType使用
     */
    private fun <T> json2Any(json: String, type: Type): T {
        return gson.fromJson(json, type)
    }

    fun <T> json2Any(json: String, raw: Class<*>, vararg args: Type): T {
        return gson.fromJson(json, getType(raw, *args))
    }

//    //List<String>的type为
//    val type = getType(List::class.java,String::class.java)
//
//    //List<List<String>>的type为
//    val type = getType(List::class.java,getType(List::class.java,String::class.java))
//
//    //Map<Int,String>的type为
//    val type = getType(List::class.java,Int::class.java,String::class.java)
//
//    //Map<String,List<String>>的类型为
//    val type = getType(Map::class.java,String::class.java, getType(List::class.java,String::class.java))
    private fun getType(raw: Class<*>, vararg args: Type) = object : ParameterizedType {
        override fun getRawType(): Type = raw
        override fun getActualTypeArguments(): Array<out Type> = args
        override fun getOwnerType(): Type? = null
    }
}