package com.example.chippermovie.screens.detailmovie

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.chippermovie.R
import com.example.chippermovie.common.screens.imageloader.ImageLoader
import com.example.chippermovie.common.utils.UrlImage
import com.example.chippermovie.networking.models.detailmovie.ProductionCompany
import kotlinx.android.synthetic.main.item_production_companie.view.*

class RecyclerProducerMovie(
    private val interaction: Interaction? = null,
    private val imageLoader: ImageLoader
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProductionCompany>() {

        override fun areItemsTheSame(
            oldItem: ProductionCompany,
            newItem: ProductionCompany
        ): Boolean {
            TODO("not implemented")
        }

        override fun areContentsTheSame(
            oldItem: ProductionCompany,
            newItem: ProductionCompany
        ): Boolean {
            TODO("not implemented")
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return ProducerMovieViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_production_companie,
                parent,
                false
            ),
            interaction,
            imageLoader
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ProducerMovieViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<ProductionCompany>) {
        differ.submitList(list)
    }

    class ProducerMovieViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?,
        private val imageLoader: ImageLoader
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: ProductionCompany) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }
            if (item.logoPath != null)
                imageLoader.loadImage(UrlImage.getUrlImage(item.logoPath), image_producer)
            tv_name_producer.text = item.name
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: ProductionCompany)
    }
}
