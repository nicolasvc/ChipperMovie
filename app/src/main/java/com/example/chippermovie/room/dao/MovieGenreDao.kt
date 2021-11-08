package com.example.chippermovie.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.chippermovie.room.entities.MovieGenreEntity


@Dao
interface MovieGenreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieGenre(movieGenreEntity: MovieGenreEntity)

    @Query("SELECT * FROM MovieGenre WHERE id=:idGenre")
    fun getGenreById(idGenre:Int) : MovieGenreEntity

}