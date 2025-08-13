package id.herald.core.util

import androidx.annotation.VisibleForTesting
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

/**
 * Created by Herald on 07/17/2023.
 */

/**
 * Extension method to simplify parsing JSON objects with [Gson].
 *
 * e.g.
 *
 * <code>
 *     val cookieList = gson.fromJson<List<Cookie>>(jsonString)
 * </code>
 */

@VisibleForTesting
var converterGson: Gson = GsonBuilder().create()

inline fun <reified T> fromJson(value: String?): T? = if (value != null) {
    try {
        converterGson.fromJson(value)
    } catch (e: Exception) {
        null
    }
} else {
    null
}

fun <T> toJson(value: T?): String? = if (value != null) {
    converterGson.toJson(value)
} else {
    null
}

inline fun <reified T> Gson.fromJson(json: String): T =
    fromJson(json, object : TypeToken<T>() {}.type)


//convert a data class to a map
fun <T> T.serializeToMap(): LinkedHashMap<String, Any?> {
    return convert()
}

//convert a map to a data class
inline fun <reified T> LinkedHashMap<String, Any>.toDataClass(): T {
    return convert()
}

//convert an object of type I to type O
inline fun <I, reified O> I.convert(): O {
    val json = converterGson.toJson(this)
    return converterGson.fromJson(json, object : TypeToken<O>() {}.type)
}
