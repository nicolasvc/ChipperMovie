package com.example.chippermovie.screens.detailmovie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.chippermovie.common.screens.activities.BaseActivity
import com.example.chippermovie.common.screens.dialog.DialogsNavigator
import com.example.chippermovie.common.viewmvc.ViewMvcFactory
import com.example.chippermovie.usecase.movie.FetchDetailMovie
import com.example.chippermovie.usecase.movie.FetchListMovie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailMovieActivity : BaseActivity(),DetailMovieMvc.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private lateinit var movieId:Number

    @Inject
    lateinit var viewMvcFactory: ViewMvcFactory

    @Inject
    lateinit var fetchDetailMovieMvc: FetchDetailMovie

    @Inject
    lateinit var dialogsNavigator: DialogsNavigator


    private lateinit var detailMovieMvc: DetailMovieMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInjection()
        detailMovieMvc  = viewMvcFactory.newDetailMoviewMvc(null)
        setContentView(detailMovieMvc.rootView)
        movieId = intent.extras!!.getInt(EXTRA_MOVIE_ID)
    }

    private fun initInjection() {
        injector.inject(this)
    }

    override fun onStart() {
        super.onStart()
        detailMovieMvc.registerListener(this)
        getDetailMovie()
    }

    private fun getDetailMovie() {
        coroutineScope.launch {
            try {
               when(val result = fetchDetailMovieMvc.fetchDetailMovie(movieId as Int)){
                   is FetchDetailMovie.Result.Success->{
                       detailMovieMvc.bindDetailsMovies(result.detailMovie)
                   }
                   is FetchDetailMovie.Result.Failure -> {
                       onFetchFailed()
                   }
               }
            }finally {

            }


        }
    }


    private fun onFetchFailed() {
        dialogsNavigator.showServerErrorDialog()
    }


    companion object {
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        fun start(context: Context,movieId:Int){
            val intent = Intent(context,DetailMovieActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID,movieId)
            context.startActivity(intent)
        }
    }


}