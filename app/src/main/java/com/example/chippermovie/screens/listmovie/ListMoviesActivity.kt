package com.example.chippermovie.screens.listmovie

import android.os.Bundle
import com.example.chippermovie.common.screens.ScreensNavigator
import com.example.chippermovie.common.screens.activities.BaseActivity
import com.example.chippermovie.common.viewmvc.ViewMvcFactory
import com.example.chippermovie.networking.models.movie.Movie
import com.example.chippermovie.usecase.movie.FetchListMovie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListMoviesActivity : BaseActivity() , ListMovieViewMvc.Listener {


    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private var pagination:Int = 1

    @Inject
    lateinit var viewMvcFactory: ViewMvcFactory

    @Inject
    lateinit var fetchListMovie:FetchListMovie

    @Inject
    lateinit var screensNavigator: ScreensNavigator

    private lateinit var listMovieViewMvc:ListMovieViewMvc


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInjection()
        listMovieViewMvc = viewMvcFactory.newListMovieMvc(null)
        setContentView(listMovieViewMvc.rootView)


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
        screensNavigator.toDetailMovie(movieClicked.id)
    }

    override fun onLoadMoreMovies() {
        pagination += 1
        getMovies()
    }
}