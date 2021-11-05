package com.example.chippermovie.networking.models.detailmovie


import com.google.gson.annotations.SerializedName

data class DetailMovie(
    @SerializedName("adult")
    val adult: Boolean, // false
    @SerializedName("backdrop_path")
    val backdropPath: String, // /briUEoIqNxvo9Q7tsDI7hzCf6GG.jpg
    @SerializedName("belongs_to_collection")
    val belongsToCollection: Any, // null
    @SerializedName("budget")
    val budget: Int, // 20000000
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("homepage")
    val homepage: String, // http://www.foxsearchlight.com/thedescendants/
    @SerializedName("id")
    val id: Int, // 65057
    @SerializedName("imdb_id")
    val imdbId: String, // tt1033575
    @SerializedName("original_language")
    val originalLanguage: String, // en
    @SerializedName("original_title")
    val originalTitle: String, // The Descendants
    @SerializedName("overview")
    val overview: String, // With his wife Elizabeth on life support after a boating accident, Hawaiian land baron, Matt King takes his daughters on a trip from Oahu to Kauai to confront a young real estate broker, who was having an affair with Elizabeth before her misfortune.
    @SerializedName("popularity")
    val popularity: Double, // 24.786
    @SerializedName("poster_path")
    val posterPath: String, // /mCpYsKQNOtaBYJWCRYYuFmLsB6X.jpg
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry>,
    @SerializedName("release_date")
    val releaseDate: String, // 2011-09-09
    @SerializedName("revenue")
    val revenue: Int, // 177243185
    @SerializedName("runtime")
    val runtime: Int, // 115
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>,
    @SerializedName("status")
    val status: String, // Released
    @SerializedName("tagline")
    val tagline: String, // The South Pacific ain't that terrific.
    @SerializedName("title")
    val title: String, // The Descendants
    @SerializedName("video")
    val video: Boolean, // false
    @SerializedName("vote_average")
    val voteAverage: Double, // 6.8
    @SerializedName("vote_count")
    val voteCount: Int // 2144
)