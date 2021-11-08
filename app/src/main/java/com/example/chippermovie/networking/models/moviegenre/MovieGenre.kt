package com.example.chippermovie.networking.models.moviegenre


import com.google.gson.annotations.SerializedName

data class MovieGenre(
    @SerializedName("id")
    val id: Int, // 10770
    @SerializedName("name")
    val name: String // TV Movie
)