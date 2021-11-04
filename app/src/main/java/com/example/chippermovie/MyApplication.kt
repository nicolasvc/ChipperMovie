package com.example.chippermovie

import android.app.Application
import com.example.chippermovie.di.app.AppComponent
import com.example.chippermovie.di.app.AppModule
import com.example.chippermovie.di.app.DaggerAppComponent

class MyApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }


    override fun onCreate() {
        super.onCreate()
    }
}