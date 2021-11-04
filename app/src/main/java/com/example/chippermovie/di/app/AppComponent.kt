package com.example.chippermovie.di.app

import android.app.Application
import com.example.chippermovie.di.presentation.PresentationComponent
import com.example.chippermovie.networking.MovieDatabaseApi
import dagger.Component


@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun application():Application

    fun moviedatabaseApi():MovieDatabaseApi

    fun newPresentationComponent():PresentationComponent

    //TODO CREAR EL COMPONENTE DE LA ACTIVIDAD

}