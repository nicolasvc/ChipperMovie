package com.example.chippermovie.networking

import com.example.chippermovie.networking.models.movie.Movie
import com.google.gson.annotations.SerializedName

data class MovieListResponseSchema(
    @SerializedName("results") val movies: List<Movie>,
    @SerializedName("total_pages") val pages_movies:Int,
    @SerializedName("total_results") val total_result:Int
)
