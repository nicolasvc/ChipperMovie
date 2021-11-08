package com.example.chippermovie.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.chippermovie.room.dao.MovieGenreDao
import com.example.chippermovie.room.entities.MovieGenreEntity


@Database(entities = [MovieGenreEntity::class],version = 1)
abstract class MovieRoomDatabase : RoomDatabase() {

    abstract fun movieGenreDao(): MovieGenreDao

    companion object {
        @Volatile
        private var instance: MovieRoomDatabase? = null
        private val LOCK = Any()

        fun getDataBase(context: Context): MovieRoomDatabase = instance ?: synchronized(LOCK) {
            instance ?: builDataBase(context).also { instance = it }
        }

        private fun builDataBase(context: Context) =
            Room.databaseBuilder(context, MovieRoomDatabase::class.java, "MovieRoomDatabase").build()


    }

}