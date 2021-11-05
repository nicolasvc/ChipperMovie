package com.example.chippermovie.networking.models.detailmovie


import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("id")
    val id: Int, // 35
    @SerializedName("name")
    val name: String // Comedy
)