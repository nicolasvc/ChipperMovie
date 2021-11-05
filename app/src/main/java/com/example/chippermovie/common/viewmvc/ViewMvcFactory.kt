package com.example.chippermovie.common.viewmvc

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.chippermovie.common.screens.imageloader.ImageLoader
import com.example.chippermovie.screens.detailmovie.DetailMovieMvc
import com.example.chippermovie.screens.listmovie.ListMovieViewMvc
import javax.inject.Inject

class ViewMvcFactory @Inject constructor(
    private val layoutInflater: LayoutInflater,
    private val imageLoader: ImageLoader
) {

    fun newDetailMoviewMvc(parentViewGroup: ViewGroup?):DetailMovieMvc = DetailMovieMvc(layoutInflater,parentViewGroup,imageLoader)

    fun newListMovieMvc(parentViewGroup: ViewGroup?):ListMovieViewMvc = ListMovieViewMvc(layoutInflater,parentViewGroup)

}