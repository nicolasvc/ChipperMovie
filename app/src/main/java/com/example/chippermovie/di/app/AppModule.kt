package com.example.chippermovie.di.app

import android.app.Application
import com.example.chippermovie.Constants
import com.example.chippermovie.networking.MovieDatabaseApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
class AppModule(val application: Application) {


    @Provides
    @AppScope
    fun retrofit():Retrofit = Retrofit.Builder().baseUrl(Constants.URL_MOVIE_DATABASE).addConverterFactory(GsonConverterFactory.create()).build()


    @Provides
    fun application() = application


    @Provides
    @AppScope
    fun moviedatabaseApi(retrofit: Retrofit):MovieDatabaseApi =  retrofit.create(MovieDatabaseApi::class.java)

}