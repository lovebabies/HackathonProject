package com.example.android.flinkgoangelhack.util

import android.content.Context
import android.content.SharedPreferences
import java.lang.UnsupportedOperationException

class PreferencesUtil(private val context: Context) {
    private var mPref = context.getSharedPreferences("Preferences", Context.MODE_PRIVATE)

    var token: String?
        get() = mPref["token"]
        set(value) {
            mPref["token"] = value
        }

    var avatarUrl: String?
        get() = mPref["avatarUrl"]
        set(value) {
            mPref["avatarUrl"] = value
        }

    var userName: String?
        get() = mPref["userName"]
        set(value) {
            mPref["userName"] = value
        }

    inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        var editor = this.edit()
        operation(editor)
        editor.apply()
    }

    operator fun SharedPreferences.set(key: String, value: Any?) {
        when(value) {
            is String? -> edit { it.putString(key, value) }
            is Int -> edit{it.putInt(key, value)}
            is Long -> edit { it.putLong(key, value) }
            is Float -> edit { it.putFloat(key, value) }
            is Boolean -> edit {it.putBoolean(key, value)}
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    inline operator fun <reified T: Any> SharedPreferences.get(key: String, defaultValue: T? = null): T? {
        return when (T::class) {
            String::class -> getString(key, defaultValue as? String) as T?
            Int::class -> getInt(key, defaultValue as? Int ?: -1) as T?
            Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
            Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T?
            Long::class -> getLong(key, defaultValue as? Long ?: -1) as T?
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }
}