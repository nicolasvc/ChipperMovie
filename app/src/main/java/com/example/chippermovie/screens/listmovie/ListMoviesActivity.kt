package com.example.chippermovie.screens.listmovie

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.chippermovie.R
import com.example.chippermovie.common.screens.ScreensNavigator
import com.example.chippermovie.common.screens.activities.BaseActivity
import com.example.chippermovie.common.screens.activities.ModalBottomSheet
import com.example.chippermovie.common.viewmvc.ViewMvcFactory
import com.example.chippermovie.networking.models.movie.Movie
import com.example.chippermovie.usecase.movie.FetchListMovie
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
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


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_filter -> {
                ShowBottomSheetFragment()
            }
        }
        return super.onOptionsItemSelected(item)
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
                        listMovieViewMvc.bindPagesMovies(result.movies.pages_movies)
                        listMovieViewMvc.bindSizeList(result.movies.total_result)
                        listMovieViewMvc.bindMovies(result.movies.movies)
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
    fun ShowBottomSheetFragment() {
        val mBottomSheetFragment = ModalBottomSheet()
        mBottomSheetFragment.show(supportFragmentManager, "MY_BOTTOM_SHEET")
    }


    override fun onRefreshClicked() {
        getMovies()
    }

    override fun onQuestionClicked(movieClicked: Movie) {
        screensNavigator.toDetailMovie(movieClicked.id)
    }

    override fun onLoadMoreMovies(currentPage : Int) {
        pagination = currentPage
        getMovies()
    }
}