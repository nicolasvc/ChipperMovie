package com.example.chippermovie.screens.listmovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.chippermovie.R
import com.example.chippermovie.common.decoration.TopSpacingItemDecoration
import com.example.chippermovie.common.viewmvc.BaseViewMvc
import com.example.chippermovie.networking.models.movie.Movie
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
    private val recyclerView: RecyclerView
    private lateinit var moviesAdapter: RecyclerMoviesAdapter
    var isLoading = false
    private var listMovies: List<Movie> = mutableListOf()
    private var sizeListMovie: Int = 0
    private var paginationMovie: Int = 0
    private var currentPage: Int = 1


    init {
        addListenerSwipe()
        recyclerView = findViewById(R.id.recycler_movies)
        initRecyclerView()
        initScrollListener()
    }


    private fun initRecyclerView() {
        recyclerView.layoutManager = GridLayoutManager(context, 2)
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


    fun bindSizeList(totalResultsMovie: Int) {
        if (totalResultsMovie.mod(2) == 1)
            totalResultsMovie + 1

        sizeListMovie += totalResultsMovie
        sizeListMovie /= 2
    }

    fun bindPagesMovies(pagesMovie: Int) {
        paginationMovie = pagesMovie
    }

    fun bindMovies(movies: List<Movie>) {
        listMovies.toMutableList().addAll(movies)
        moviesAdapter.submitList(movies)
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
        if (paginationMovie > currentPage + 1){
            showSnackBar()
        }else{
            currentPage + 1
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