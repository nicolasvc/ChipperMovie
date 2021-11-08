package com.example.chippermovie.screens.listmovie

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.chippermovie.Constants
import com.example.chippermovie.R
import com.example.chippermovie.common.decoration.TopSpacingItemDecoration
import com.example.chippermovie.common.viewmvc.BaseViewMvc
import com.example.chippermovie.networking.MovieGenreSchema
import com.example.chippermovie.networking.models.movie.Movie
import com.example.chippermovie.repository.genrerepository.MovieGenreRepository
import com.example.chippermovie.room.entities.MovieGenreEntity
import com.google.android.material.snackbar.Snackbar


class ListMovieViewMvc(layoutInflater: LayoutInflater, parent: ViewGroup?) :
    BaseViewMvc<ListMovieViewMvc.Listener>(layoutInflater, parent, R.layout.activity_main),
    RecyclerMoviesAdapter.Interaction {


    interface Listener {
        fun onRefreshClicked()
        fun onQuestionClicked(movieClicked: Movie)
        fun onLoadMoreMovies(currentPage: Int)
    }

    private val swipeRefreshLayout: SwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
    private val constrainFather: ConstraintLayout = findViewById(R.id.constrain_padre)
    private val textViewNetworkStatus:TextView = findViewById(R.id.textViewNetworkStatus)
    private val networkStatusLayout:LinearLayout = findViewById(R.id.networkStatusLayout)
    private val recyclerView: RecyclerView
    private lateinit var moviesAdapter: RecyclerMoviesAdapter
    var isLoading = false
    private var listMovies: List<Movie> = mutableListOf()
    private var sizeListMovie: Int = 0
    private var paginationMovie: Int = 0
    private var currentPage: Int = 1
    private val movieGenreRepository:MovieGenreRepository = MovieGenreRepository()


    init {
        addListenerSwipe()
        recyclerView = findViewById(R.id.recycler_movies)
        initRecyclerView()
        initScrollListener()
    }


    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        val topSpacing = TopSpacingItemDecoration(10)
        recyclerView.addItemDecoration(topSpacing)
        moviesAdapter = RecyclerMoviesAdapter(this)
        recyclerView.adapter = moviesAdapter
    }

    private fun addListenerSwipe() {
        swipeRefreshLayout.setOnRefreshListener {
            for (listener in listeners) {
                listener.onRefreshClicked()
            }
        }
    }


    private fun initScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == sizeListMovie - 1) {
                        validatePaginationMovie()
                        isLoading = true
                    }
                }
            }
        })
    }

    fun saveGenreMovie(movieGenre: MovieGenreSchema) {
        movieGenre.genres.forEach {  genre ->
            movieGenreRepository.insertMovieGenre(
                MovieGenreEntity(genre.id,genre.name)
            )
        }
    }

    fun notifyConnectionLost(){
        textViewNetworkStatus.text = context.getString(R.string.text_no_connectivity)
        networkStatusLayout.apply {
            View.VISIBLE
            setBackgroundColor(context.getColor(R.color.colorStatusNotConnected))
        }
    }

    fun notifyBackOnline(){
        if (moviesAdapter.itemCount == 0) {
            loadMoreMovies()
        }
        textViewNetworkStatus.text = context.getString(R.string.text_connectivity)
        networkStatusLayout.apply {
            setBackgroundColor(context.getColor(R.color.colorStatusConnected))
            animate()
                .alpha(1f)
                .setStartDelay(Constants.ANIMATION_DURATION)
                .setDuration(Constants.ANIMATION_DURATION)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        View.GONE
                    }
                })
        }
    }


    fun bindSizeList() {
        sizeListMovie = listMovies.size
    }

    fun bindPagesMovies(pagesMovie: Int) {
        paginationMovie = pagesMovie
    }

    fun bindMovies(movies: List<Movie>) {
        listMovies = ArrayList(movies.map { it.copy() })
        moviesAdapter.submitList(listMovies)
    }

    fun showProgressIndication() {
        swipeRefreshLayout.isRefreshing = true
    }

    fun hideProgressIndication() {
        if (swipeRefreshLayout.isRefreshing) {
            swipeRefreshLayout.isRefreshing = false
        }
    }

    fun validatePaginationMovie() {
        if (currentPage + 1 > paginationMovie){
            showSnackBar()
        }else{
            currentPage  += currentPage + 1
            loadMoreMovies()
        }
    }

    private fun loadMoreMovies(){
        for (listener in listeners) {
            listener.onLoadMoreMovies(currentPage)
        }
    }


    private fun showSnackBar() {
        Snackbar.make(
            constrainFather,
            "Ya se consulto todas las peliculas de esta categoria",
            Snackbar.LENGTH_LONG
        ).show()
    }


    override fun onItemSelected(item: Movie) {
        for (listener in listeners) {
            listener.onQuestionClicked(item)
        }
    }


}