package com.example.chippermovie.networking

import com.example.chippermovie.networking.models.moviegenre.MovieGenre
import com.google.gson.annotations.SerializedName

data class MovieGenreSchema(
    @SerializedName("genres")
    val genres:List<MovieGenre>
)