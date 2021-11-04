package com.example.chippermovie.di.app

import android.app.Application
import com.example.chippermovie.di.presentation.PresentationComponent
import com.example.chippermovie.networking.MoviedatabaseApi
import dagger.Component


@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun application():Application

    fun moviedatabaseApi():MoviedatabaseApi

    fun newPresentationComponent():PresentationComponent

    //TODO CREAR EL COMPONENTE DE LA ACTIVIDAD

}