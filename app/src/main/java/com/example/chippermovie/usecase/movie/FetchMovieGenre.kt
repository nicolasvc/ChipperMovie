package com.example.chippermovie.usecase.movie

import com.example.chippermovie.networking.MovieDatabaseApiV3
import com.example.chippermovie.networking.MovieGenreSchema
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchMovieGenre @Inject constructor(private val movieDatabaseApiV3: MovieDatabaseApiV3) {


    sealed class Result{
        data class Success(val movieGenre: MovieGenreSchema) :Result()
        object Failure:Result()
    }

    suspend fun fetchMovieGenre(): Result {

        return withContext(Dispatchers.IO){

            try {
                val response = movieDatabaseApiV3.getMovieGenre()
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