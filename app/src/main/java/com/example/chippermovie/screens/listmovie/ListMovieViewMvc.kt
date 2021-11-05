package com.example.chippermovie.screens.listmovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.chippermovie.R
import com.example.chippermovie.common.decoration.TopSpacingItemDecoration
import com.example.chippermovie.common.viewmvc.BaseViewMvc
import com.example.chippermovie.networking.models.movie.Movie


class ListMovieViewMvc(layoutInflater: LayoutInflater, parent: ViewGroup?) :
    BaseViewMvc<ListMovieViewMvc.Listener>(layoutInflater, parent, R.layout.activity_main),RecyclerMoviesAdapter.Interaction {


    interface Listener {
        fun onRefreshClicked()
        fun onQuestionClicked(movieClicked: Movie)
        fun onLoadMoreMovies()
    }

    private val swipeRefreshLayout:SwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
    private val recyclerView:RecyclerView
    private lateinit var moviesAdapter:RecyclerMoviesAdapter
    var isLoading = false


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
            for (listener in listeners){
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
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == 20-1) {
                        //bottom of list!
                        for (listener in listeners){
                            listener.onLoadMoreMovies()
                        }
                        isLoading = true
                    }
                }
            }
        })
    }

    fun bindMovies(movies:List<Movie>){
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

    override fun onItemSelected(item: Movie) {
        for (listener in listeners) {
            listener.onQuestionClicked(item)
        }
    }


}