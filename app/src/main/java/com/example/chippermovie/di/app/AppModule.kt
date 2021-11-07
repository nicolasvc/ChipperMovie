package com.example.chippermovie.di.app

import android.app.Application
import com.example.chippermovie.di.RetrofitV4
import com.example.chippermovie.di.RetrofitV3
import com.example.chippermovie.networking.MovieDatabaseApiV4
import com.example.chippermovie.networking.MovieDatabaseApiV3
import com.example.chippermovie.networking.urlprovider.UrlProvider
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
class AppModule(val application: Application) {


    @Provides
    @RetrofitV4
    @AppScope
    fun retrofitV4(urlProvider: UrlProvider):Retrofit =
        Retrofit.Builder()
            .baseUrl(urlProvider.getBaseMovieV4())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @RetrofitV3
    @AppScope
    fun retrofitV3(urlProvider: UrlProvider):Retrofit =
        Retrofit.Builder()
            .baseUrl(urlProvider.getBaseMovieV3())
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    fun application() = application


    @AppScope
    @Provides
    fun urlProvider() = UrlProvider()


    @Provides
    @AppScope
    fun movieDatabaseApiV4(@RetrofitV4 retrofit: Retrofit):MovieDatabaseApiV4 =  retrofit.create(MovieDatabaseApiV4::class.java)

    @Provides
    @AppScope
    fun movieDatabaseApiV3(@RetrofitV3 retrofit: Retrofit):MovieDatabaseApiV3 =  retrofit.create(MovieDatabaseApiV3::class.java)



}