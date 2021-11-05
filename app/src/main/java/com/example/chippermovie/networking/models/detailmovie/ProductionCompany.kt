package com.example.chippermovie.networking.models.detailmovie


import com.google.gson.annotations.SerializedName

data class ProductionCompany(
    @SerializedName("id")
    val id: Int, // 444
    @SerializedName("logo_path")
    val logoPath: String, // /42UPdZl6B2cFXgNUASR8hSt9mpS.png
    @SerializedName("name")
    val name: String, // Dune Entertainment
    @SerializedName("origin_country")
    val originCountry: String // US
)