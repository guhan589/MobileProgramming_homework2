package com.example.mobileprogramming_homework2

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class Preference_id(context: Context, id:String){


    val preference: SharedPreferences = context.getSharedPreferences(id, Context.MODE_PRIVATE)


    fun getString(key: String): String {
        return preference.getString(key,"").toString()
    }
    fun setString(key: String, value: String) {
        preference.edit().putString(key, value).apply()

    }


}


