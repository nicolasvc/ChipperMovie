package com.example.chippermovie.di.presentation

import com.example.chippermovie.screens.detailmovie.DetailMovieActivity
import com.example.chippermovie.screens.listmovie.ListMoviesActivity
import dagger.Subcomponent


@PresentationScope
@Subcomponent
interface PresentationComponent {

    fun inject(listMoviesActivity: ListMoviesActivity)

    fun inject(detailMovieActivity: DetailMovieActivity)

}