package com.example.chippermovie

import android.os.Bundle
import com.example.chippermovie.common.screens.BaseActivity
import com.example.chippermovie.usecase.movie.FetchListMovie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListMoviesActivity : BaseActivity() {


    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private var pagination:Int = 1

    @Inject
    lateinit var fetchListMovie:FetchListMovie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initInyection()
        consultarMovies()
    }

    private fun initInyection() {
       injector.inject(this)
    }

    private fun consultarMovies() {
        coroutineScope.launch {
            try {
                when(fetchListMovie.fetchListMovie(pagination)){
                    is FetchListMovie.Result.Success->{
                        //TODO MOSTRAR DATA
                    }
                    is FetchListMovie.Result.Failure -> {
                        //TODO MOSTRAR ERROR
                    }
                }
            }finally {

            }
        }
    }
}