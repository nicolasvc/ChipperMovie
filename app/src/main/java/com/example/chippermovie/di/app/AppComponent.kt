package com.example.chippermovie.di.app

import android.app.Application
import com.example.chippermovie.di.activity.ActivityComponent
import com.example.chippermovie.networking.MovieDatabaseApiV3
import com.example.chippermovie.networking.MovieDatabaseApiV4
import dagger.Component


@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun application():Application

    fun moviedatabaseApiV4(): MovieDatabaseApiV4

    fun moviedatabaseApiV3(): MovieDatabaseApiV3

    fun newActivityComponentBuilder():ActivityComponent.Builder


}