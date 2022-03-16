package me.leon.ext

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import java.util.*
import kotlin.collections.ArrayList


/**
 * <p>description： gson 工具</p> <p>author：Leon</p> <p>date：2019/3/28 0028</p>
 * <p>e-mail：deadogone@gmail.com</p>
 */
object GsonUtil {
    private val gson = Gson()

    fun toJson(s: Any): String = gson.toJson(s)

    fun <T> fromJson(json: String, clazz: Class<T>): T = gson.fromJson<T>(json, clazz)

    fun <D> jsonToArrayList(json: String?, clazz: Class<D>?): ArrayList<D>? {
        val type = object : TypeToken<ArrayList<JsonObject?>?>() {}.type
        val jsonObjects: ArrayList<JsonObject> = gson.fromJson<ArrayList<JsonObject>>(json, type)
        val arrayList = ArrayList<D>()
        for (jsonObject in jsonObjects) {
            arrayList.add(gson.fromJson(jsonObject, clazz))
        }
        return arrayList
    }
}

// json 转换扩展
fun Any.toJson() = GsonUtil.toJson(this)

fun <T> String.fromJson(clazz: Class<T>) = GsonUtil.fromJson(this, clazz)
fun <T> String.fromJsonArray(clazz: Class<T>) = GsonUtil.jsonToArrayList(this, clazz)

