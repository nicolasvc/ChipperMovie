package com.example.chippermovie.usecase.movie

import com.example.chippermovie.networking.MovieDatabaseApiV4
import com.example.chippermovie.networking.MovieListResponseSchema
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchListMovie @Inject constructor(private val movieDatabaseApiV4: MovieDatabaseApiV4) {

    sealed class Result{
        data class Success(val movies:MovieListResponseSchema) :Result()
        object Failure:Result()
    }


    suspend fun fetchListMovie(page:Int,categoryId:Int): Result{

        return withContext(Dispatchers.IO){

            try {
                val response = movieDatabaseApiV4.getListMovies(categoryId,page)
                if(response.isSuccessful && response.body() != null){
                    return@withContext Result.Success(response.body()!!)
                }else{
                    return@withContext Result.Failure
                }

            }catch (t:Throwable){
                if(t is CancellationException){
                    return@withContext Result.Failure
                }else
                    return@withContext Result.Failure
            }
        }
    }
}