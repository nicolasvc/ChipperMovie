package com.example.chippermovie.networking.models.movie


import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("adult")
    val adult: Boolean, // false
    @SerializedName("backdrop_path")
    val backdropPath: String, // /kaIfm5ryEOwYg8mLbq8HkPuM1Fo.jpg
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("id")
    val id: Int, // 284053
    @SerializedName("media_type")
    val mediaType: String, // movie
    @SerializedName("original_language")
    val originalLanguage: String, // en
    @SerializedName("original_title")
    val originalTitle: String, // Thor: Ragnarok
    @SerializedName("overview")
    val overview: String, // Thor is imprisoned on the other side of the universe and finds himself in a race against time to get back to Asgard to stop Ragnarok, the destruction of his home-world and the end of Asgardian civilization, at the hands of a powerful new threat, the ruthless Hela.
    @SerializedName("popularity")
    val popularity: Double, // 148.901
    @SerializedName("poster_path")
    val posterPath: String, // /rzRwTcFvttcN1ZpX2xv4j3tSdJu.jpg
    @SerializedName("release_date")
    val releaseDate: String, // 2017-10-25
    @SerializedName("title")
    val title: String, // Thor: Ragnarok
    @SerializedName("video")
    val video: Boolean, // false
    @SerializedName("vote_average")
    val voteAverage: Double, // 7.6
    @SerializedName("vote_count")
    val voteCount: Int // 16654
)