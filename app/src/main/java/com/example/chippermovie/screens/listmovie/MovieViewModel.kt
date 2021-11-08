package com.example.chippermovie.screens.listmovie

import androidx.lifecycle.ViewModel
import com.example.chippermovie.repository.genrerepository.MovieGenreRepository

class MovieViewModel : ViewModel() {

    private val movieGenreRepository =  MovieGenreRepository()


    fun getGenreById(idGenre:Int) = movieGenreRepository.getMovieGenreById(idGenre)


}