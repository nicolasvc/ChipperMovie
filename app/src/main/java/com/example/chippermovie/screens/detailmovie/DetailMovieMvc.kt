package com.example.chippermovie.screens.detailmovie

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chippermovie.R
import com.example.chippermovie.common.decoration.TopSpacingItemDecoration
import com.example.chippermovie.common.screens.imageloader.ImageLoader
import com.example.chippermovie.common.utils.UrlImage
import com.example.chippermovie.common.viewmvc.BaseViewMvc
import com.example.chippermovie.networking.models.detailmovie.DetailMovie
import com.example.chippermovie.networking.models.detailmovie.Genre
import com.example.chippermovie.networking.models.detailmovie.ProductionCompany

class DetailMovieMvc(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    private val imageLoader: ImageLoader
) :
    BaseViewMvc<DetailMovieMvc.Listener>(
        layoutInflater, parent,
        R.layout.activity_detail_movie
    ),RecyclerProducerMovie.Interaction {

    private lateinit var recyclerProducerAdapter : RecyclerProducerMovie


    interface Listener {

    }

    private val imagePath: ImageView = findViewById(R.id.back_image_movie)
    private val imagePoster: ImageView = findViewById(R.id.poster_movie_image)
    private val titleMovie: TextView = findViewById(R.id.title_movie)
    private val tvReleaseDate: TextView = findViewById(R.id.tv_release_date)
    private val tvLanguage: TextView = findViewById(R.id.tv_language)
    private val tvRuntime: TextView = findViewById(R.id.tv_runtime)
    private val tvOverview: TextView = findViewById(R.id.tv_overview)
    private val tvTagline: TextView = findViewById(R.id.tv_tagline)
    private val ratingMovie :RatingBar = findViewById(R.id.rating_movie)
    private val tvVoteAverage: TextView = findViewById(R.id.tv_vote_average)
    private val tvGender: TextView = findViewById(R.id.tv_gender)



    private val recyclerProducers :RecyclerView = findViewById(R.id.recycler_producers)



    init {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerProducers.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        val topSpacing = TopSpacingItemDecoration(10)
        recyclerProducers.addItemDecoration(topSpacing)
        recyclerProducerAdapter = RecyclerProducerMovie(this,imageLoader)
        recyclerProducers.adapter = recyclerProducerAdapter
    }


    fun bindDetailsMovies(detailMovie: DetailMovie) {
        imageLoader.loadImage(UrlImage.getUrlImage(detailMovie.backdropPath), imagePath)
        imageLoader.loadImage(UrlImage.getUrlImage(detailMovie.posterPath), imagePoster)
        titleMovie.text = detailMovie.title
        tvReleaseDate.text = detailMovie.releaseDate
        tvLanguage.text = detailMovie.spokenLanguages[0].englishName
        tvRuntime.text = detailMovie.runtime.toString()
        tvOverview.text = detailMovie.overview
        recyclerProducerAdapter.submitList(detailMovie.productionCompanies)
        tvTagline.text = detailMovie.tagline
        ratingMovie.rating =  if (detailMovie.voteAverage.toFloat() > 5 )  detailMovie.voteAverage.toFloat() / 2 else  detailMovie .voteAverage.toFloat()
        tvVoteAverage.text = detailMovie.voteAverage.toString()
        tvGender.text = getGender(detailMovie.genres)

    }

    private fun getGender(genres: List<Genre>): String {
        var genre = ""
        for (genreUnique in genres) {
            genre += if (genre.isEmpty()) genreUnique.name else "/${genreUnique.name}"
        }
        return genre
    }

    override fun onItemSelected(position: Int, item: ProductionCompany) {

    }

}