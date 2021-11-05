package com.example.chippermovie.screens.detailmovie

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.chippermovie.R
import com.example.chippermovie.common.screens.imageloader.ImageLoader
import com.example.chippermovie.common.viewmvc.BaseViewMvc

class DetailMovieMvc(layoutInflater: LayoutInflater, parent: ViewGroup?, private val imageLoader: ImageLoader) :
    BaseViewMvc<DetailMovieMvc.Listener>(
        layoutInflater, parent,
        R.layout.activity_detail_movie
    ) {


    interface Listener {

    }
}