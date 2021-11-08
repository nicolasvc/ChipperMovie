package com.example.chippermovie

import android.app.Application
import android.content.Context
import com.example.chippermovie.di.app.AppComponent
import com.example.chippermovie.di.app.AppModule
import com.example.chippermovie.di.app.DaggerAppComponent

class MyApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }


    init {
        instance = this
    }

    companion object {
        private var instance: MyApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }


    override fun onCreate() {
        super.onCreate()
    }
}