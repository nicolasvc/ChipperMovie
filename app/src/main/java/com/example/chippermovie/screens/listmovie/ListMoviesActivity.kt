package com.example.chippermovie.screens.listmovie

import android.os.Bundle
import android.view.LayoutInflater
import com.example.chippermovie.common.screens.BaseActivity
import com.example.chippermovie.networking.Movie
import com.example.chippermovie.usecase.movie.FetchListMovie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListMoviesActivity : BaseActivity() , ListMovieViewMvc.Listener {


    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private var pagination:Int = 1

    private lateinit var listMovieViewMvc: ListMovieViewMvc

    @Inject
    lateinit var fetchListMovie:FetchListMovie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listMovieViewMvc = ListMovieViewMvc(LayoutInflater.from(this),null)
        setContentView(listMovieViewMvc.rootView)
        initInjection()

    }

    override fun onStart() {
        super.onStart()
        listMovieViewMvc.registerListener(this)
        getMovies()
    }

    private fun initInjection() {
       injector.inject(this)
    }

    private fun getMovies() {
        coroutineScope.launch {
            listMovieViewMvc.showProgressIndication()
            try {
                when(val result = fetchListMovie.fetchListMovie(pagination)){
                    is FetchListMovie.Result.Success->{
                        listMovieViewMvc.bindMovies(result.movies)
                    }
                    is FetchListMovie.Result.Failure -> {
                        //TODO MOSTRAR ERROR
                    }
                }
            }finally {
                listMovieViewMvc.hideProgressIndication()
            }
        }
    }

    override fun onRefreshClicked() {

    }

    override fun onQuestionClicked(movieClicked: Movie) {

    }

    override fun onLoadMoreMovies() {
        pagination += 1
        getMovies()
    }
}