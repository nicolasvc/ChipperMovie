package com.example.chippermovie.networking

import com.example.chippermovie.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDatabaseApiV4 {

    @GET("list/2?api_key="+Constants.API_KEY +"&access_token="+ Constants.ACCESS_TOKEN_API)
    suspend fun getListMovies(@Query("page") page:Int) : Response<MovieListResponseSchema>

}