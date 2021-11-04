package com.example.chippermovie.di.app

import android.app.Application
import com.example.chippermovie.Constants
import com.example.chippermovie.networking.MoviedatabaseApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
class AppModule(val application: Application) {


    @Provides
    @AppScope
    fun retrofit():Retrofit = Retrofit.Builder().baseUrl(Constants.ULR_MOVIE_DATABASE).addConverterFactory(GsonConverterFactory.create()).build()


    @Provides
    fun application() = application


    @Provides
    @AppScope
    fun moviedatabaseApi(retrofit: Retrofit):MoviedatabaseApi =  retrofit.create(MoviedatabaseApi::class.java)

}