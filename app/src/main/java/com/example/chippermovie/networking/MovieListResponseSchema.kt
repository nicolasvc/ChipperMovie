package com.example.chippermovie.networking

import com.google.gson.annotations.SerializedName

data class MovieListResponseSchema(

    @SerializedName("results") val movies :List<Movie>
)
