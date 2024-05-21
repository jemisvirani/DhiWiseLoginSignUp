package com.jemissapplication.app.modules.signup.utils

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.SharedPreferences

object Pref {

    var activity: Activity? = null
    fun setInit(activity: Activity) {
        this.activity = activity
        sharePref = Pref.activity?.getPreferences(Context.MODE_PRIVATE) ?: return
    }

    private lateinit var sharePref: SharedPreferences


    fun setString(key: String, data: String) {
//        sharePref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharePref.edit()) {
            putString(key, data)
            apply()
        }
    }

    fun getString(key: String, data: String): String {
        return sharePref.getString(key, data).toString()
    }

}