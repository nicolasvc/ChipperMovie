package com.example.chippermovie.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "MovieGenre")
class MovieGenreEntity(
    @PrimaryKey
    val id: Int,
    val name: String
)