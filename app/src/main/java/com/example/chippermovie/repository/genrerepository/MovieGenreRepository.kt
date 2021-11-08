package com.example.chippermovie.repository.genrerepository

import com.example.chippermovie.MyApplication
import com.example.chippermovie.room.dao.MovieGenreDao
import com.example.chippermovie.room.database.MovieRoomDatabase
import com.example.chippermovie.room.entities.MovieGenreEntity
import java.util.concurrent.Executors


class MovieGenreRepository {

    private val movieGenreDao:MovieGenreDao

    init {
        val dataBase = MovieRoomDatabase.getDataBase(MyApplication.applicationContext())
        movieGenreDao = dataBase.movieGenreDao()
    }

    fun getMovieGenreById(movieGenreId:Int) : MovieGenreEntity = movieGenreDao.getGenreById(movieGenreId)

    fun insertMovieGenre(movieGenre:MovieGenreEntity){
        val executor = Executors.newSingleThreadExecutor()
        executor.execute{
            movieGenreDao.insertMovieGenre(movieGenre)
        }
    }

}