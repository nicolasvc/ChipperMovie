package com.example.chippermovie.di.activity

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.chippermovie.common.screens.ScreensNavigator
import dagger.Module
import dagger.Provides


@Module
object ActivityModule {


    @Provides
    fun layoutInflater(activity: AppCompatActivity): LayoutInflater = LayoutInflater.from(activity)

    @Provides
    @ActivityScope
    fun screenNavigator(activity: AppCompatActivity): ScreensNavigator = ScreensNavigator(activity)

    @Provides
    fun fragmentManager(activity: AppCompatActivity) = activity.supportFragmentManager
}