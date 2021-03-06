package com.example.chippermovie.networking

import com.example.chippermovie.Constants
import com.example.chippermovie.networking.models.detailmovie.DetailMovie
import com.example.chippermovie.networking.models.moviegenre.MovieGenre
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDatabaseApiV3 {

    @GET(Constants.GET_DETAIL_MOVIE+Constants.API_KEY +"&language=en-US")
    suspend fun getDetailMovie(@Path("movieId") movieId:Int) : Response<DetailMovie>

    @GET(Constants.GET_GENRE_MOVIE+Constants.API_KEY)
    suspend fun getMovieGenre() : Response<MovieGenreSchema>

}