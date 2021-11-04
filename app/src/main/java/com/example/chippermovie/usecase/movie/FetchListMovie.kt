package com.example.chippermovie.usecase.movie

import com.example.chippermovie.networking.Movie
import com.example.chippermovie.networking.MovieDatabaseApi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchListMovie @Inject constructor(private val movieDatabaseApi: MovieDatabaseApi) {

    sealed class Result{
        data class Success(val movies:List<Movie>) :Result()
        object Failure:Result()
    }


    suspend fun fetchListMovie(page:Int): Result{

        return withContext(Dispatchers.IO){

            try {
                val response = movieDatabaseApi.getListMovies(page)
                if(response.isSuccessful && response.body() != null){
                    return@withContext Result.Success(response.body()!!.movies)
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