package com.example.chippermovie.screens.listmovie

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.example.chippermovie.R
import com.example.chippermovie.common.screens.ScreensNavigator
import com.example.chippermovie.common.screens.activities.BaseActivity
import com.example.chippermovie.common.screens.activities.ModalBottomSheet
import com.example.chippermovie.common.screens.dialog.DialogsNavigator
import com.example.chippermovie.common.utils.network.NetworkUtils
import com.example.chippermovie.common.viewmvc.ViewMvcFactory
import com.example.chippermovie.networking.models.movie.Movie
import com.example.chippermovie.usecase.movie.FetchListMovie
import com.example.chippermovie.usecase.movie.FetchMovieGenre
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListMoviesActivity : BaseActivity() , ListMovieViewMvc.Listener,ModalBottomSheet.InteractionBottomSheet {


    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private var pagination:Int = 1
    private var categoryMovie:Int = 1

    @Inject
    lateinit var viewMvcFactory: ViewMvcFactory

    @Inject
    lateinit var fetchListMovie:FetchListMovie

    @Inject
    lateinit var fetchMovieGenre: FetchMovieGenre

    @Inject
    lateinit var screensNavigator: ScreensNavigator

    @Inject
    lateinit var dialogsNavigator: DialogsNavigator

    private lateinit var listMovieViewMvc:ListMovieViewMvc

    private lateinit var movieViewModel:MovieViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInjection()
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        listMovieViewMvc = viewMvcFactory.newListMovieMvc(null)
        setContentView(listMovieViewMvc.rootView)
    }

    override fun onStart() {
        super.onStart()
        listMovieViewMvc.registerListener(this)
        //getGenreMovie()
        handleNetworkChanges()
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
                showBottomSheetFragment()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initInjection() {
       injector.inject(this)
    }


    private fun getGenreMovie() {
        coroutineScope.launch {
            try {
                when(val result = fetchMovieGenre.fetchMovieGenre()){
                    is FetchMovieGenre.Result.Success->{
                        listMovieViewMvc.saveGenreMovie(result.movieGenre)
                    }
                    is FetchMovieGenre.Result.Failure -> {
                        //TODO MOSTRAR ERROR
                    }
                }
            }finally {
            }
        }

    }

    private fun getMovies() {
        coroutineScope.launch {
            listMovieViewMvc.showProgressIndication()
            try {
                when(val result = fetchListMovie.fetchListMovie(pagination,categoryMovie)){
                    is FetchListMovie.Result.Success->{
                        listMovieViewMvc.bindMovies(result.movies.movies)
                        listMovieViewMvc.bindPagesMovies(result.movies.pages_movies)
                        listMovieViewMvc.bindSizeList()

                    }
                    is FetchListMovie.Result.Failure -> {
                        onFetchFailed()
                    }
                }
            }finally {
                listMovieViewMvc.hideProgressIndication()
            }
        }
    }

    private fun handleNetworkChanges() {
        NetworkUtils.getNetworkLiveData(applicationContext).observe(this) { isConnected ->
            if (!isConnected) {
                listMovieViewMvc.notifyConnectionLost()
            } else {
                listMovieViewMvc.notifyBackOnline()
            }
        }
    }


    private fun showBottomSheetFragment() {
        val mBottomSheetFragment = ModalBottomSheet()
        mBottomSheetFragment.show(supportFragmentManager, "MY_BOTTOM_SHEET")
    }

    private fun onFetchFailed() {
        dialogsNavigator.showServerErrorDialog()
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

    override fun onClickItem(idCategoryMovie: Int) {
        categoryMovie = idCategoryMovie
        pagination = 1
        getMovies()
    }
}