package com.example.mobileprogramming_homework2

import android.content.Context
import android.content.SharedPreferences

class Preference_name(context: Context, name:String) {

    val preference: SharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)


    fun getString(key: String): String {
        return preference.getString(key,"").toString()
    }
    fun setString(key: String, value: String) {
        preference.edit().putString(key, value).apply()

    }

}