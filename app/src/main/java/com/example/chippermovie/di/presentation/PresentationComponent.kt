package com.example.chippermovie.di.presentation

import com.example.chippermovie.ListMoviesActivity
import dagger.Subcomponent


@PresentationScope
@Subcomponent
interface PresentationComponent {

    fun inject(listMoviesActivity: ListMoviesActivity)


}