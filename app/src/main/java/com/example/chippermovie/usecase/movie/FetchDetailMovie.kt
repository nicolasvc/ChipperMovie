package com.example.chippermovie.usecase.movie

import com.example.chippermovie.networking.MovieDatabaseApiV3
import com.example.chippermovie.networking.MovieDatabaseApiV4
import com.example.chippermovie.networking.models.detailmovie.DetailMovie
import com.example.chippermovie.networking.models.movie.Movie
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchDetailMovie @Inject constructor(private val movieDatabaseApiV3: MovieDatabaseApiV3) {

    sealed class Result{
        data class Success(val detailMovie: DetailMovie) :Result()
        object Failure:Result()
    }


    suspend fun fetchDetailMovie(movieId:Int): Result{

        return withContext(Dispatchers.IO){

            try {
                val response = movieDatabaseApiV3.getDetailMovie(movieId)
                if(response.isSuccessful && response.body() != null){
                    return@withContext Result.Success(response.body()!!)
                }else{
                    return@withContext Result.Failure
                }

            }catch (t:Throwable){
                if(t is CancellationException){
                    return@withContext Result.Failure
                }else
                    throw t
            }
        }
    }
}