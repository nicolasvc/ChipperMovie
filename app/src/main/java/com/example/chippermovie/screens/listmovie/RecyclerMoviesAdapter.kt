package com.example.chippermovie.screens.listmovie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chippermovie.Constants
import com.example.chippermovie.R
import com.example.chippermovie.networking.models.movie.Movie
import kotlinx.android.synthetic.main.item_movie_layout.view.*


class RecyclerMoviesAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1


    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.equals(newItem)
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun getItemViewType(position: Int): Int {
        return if (differ.currentList[position] == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType === VIEW_TYPE_ITEM) {
            MoviesViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_movie_layout,
                    parent,
                    false
                ),
                interaction)
        } else {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_loading, parent, false)
            LoadingViewHolder(view)
        }



    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MoviesViewHolder -> {
                holder.bind(differ.currentList[position])
            }
            is LoadingViewHolder -> {
                showLoadingView(holder,position)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Movie>) {
        differ.submitList(list)
    }

    private class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
    }

    private fun showLoadingView(viewHolder: LoadingViewHolder, position: Int) {
        viewHolder.progressBar.visibility = View.VISIBLE
    }


    class MoviesViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Movie) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(item)
            }
            itemView.text_view_tittle_movie.text = item.title
            Glide.with(itemView.context).load(Constants.URL_IMAGE_DATABASE+item.posterPath).into(itemView.image_view)
        }
    }

    interface Interaction {
        fun onItemSelected(item: Movie)
    }
}
