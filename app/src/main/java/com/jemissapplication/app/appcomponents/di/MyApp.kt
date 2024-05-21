package com.jemissapplication.app.appcomponents.di

import android.app.Application


class MyApp : Application() {

    public override fun onCreate(): Unit {
        super.onCreate()
        instance = this


    }


    public companion object {

        private lateinit var instance: MyApp

        public fun getInstance(): MyApp = instance
    }
}