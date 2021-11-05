package com.example.chippermovie.common.screens

import androidx.appcompat.app.AppCompatActivity
import com.example.chippermovie.screens.detailmovie.DetailMovieActivity

class ScreensNavigator (private val activity:AppCompatActivity) {

    fun navigateBack(){
        activity.onBackPressed()
    }

    fun toDetailMovie(movieId:Int){
        DetailMovieActivity.start(activity,movieId)
    }

}