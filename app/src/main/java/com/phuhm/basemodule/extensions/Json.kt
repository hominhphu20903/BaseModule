package com.phuhm.basemodule.extensions

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

fun <T> T.toJson(): String {
    val gson = Gson()
    return gson.toJson(this)
}

fun <T> String.fromJson(clazz: Class<T>): T? {
    return try {
        val gson = Gson()
        gson.fromJson(this, clazz)
    } catch (e: Exception) {
        null
    }
}

fun <T> List<T>.toJson(): String {
    val gson = Gson()
    return gson.toJson(this)
}

fun <T> String.fromJsonList(clazz: Class<T>): List<T>? {
    val gson = Gson()
    val type = TypeToken.getParameterized(List::class.java, clazz).type
    return gson.fromJson(this, type)
}

fun <T> Context.loadJsonFromAssetsToList(fileName: String, clazz: Class<T>): List<T> {
    return try {
        val inputStream = this.assets.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val type = TypeToken.getParameterized(List::class.java, clazz).type
        Gson().fromJson(json, type)
    } catch (e: IOException) {
        e.printStackTrace()
        emptyList()
    }
}

fun <T> Context.loadJsonFromAssetsToObject(context: Context, fileName: String, clazz: Class<T>): T? {
    return try {
        val inputStream = context.assets.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        Gson().fromJson(json, clazz)
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}

