package com.example.chippermovie.common.screens.activities

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.chippermovie.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

open class ModalBottomSheet : BottomSheetDialogFragment(),View.OnClickListener {

    private lateinit var interactionBottomSheet:InteractionBottomSheet


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.tv_marvel_universe).setOnClickListener(this)
        view.findViewById<TextView>(R.id.oscar_best_picture_2).setOnClickListener(this)
        view.findViewById<TextView>(R.id.dc_comics).setOnClickListener(this)
        view.findViewById<TextView>(R.id.oscar_best_picture_1).setOnClickListener(this)
        view.findViewById<TextView>(R.id.avengers).setOnClickListener(this)
        view.findViewById<TextView>(R.id.film_deja).setOnClickListener(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is InteractionBottomSheet){
            interactionBottomSheet = context
        }else{
            throw RuntimeException(context.toString()+ "Needs implemented InteractionBottomSheet interface")
        }
    }

    interface InteractionBottomSheet{
        fun onClickItem(idCategoryMovie:Int)
    }

    override fun onClick(v: View?) {
        val tvSelected = v as TextView
        interactionBottomSheet.onClickItem(getCategoryMovie(tvSelected.id))
        dismiss()
    }

    private fun getCategoryMovie(id: Int): Int {
        return when(id){
            R.id.tv_marvel_universe -> 1
            R.id.oscar_best_picture_2 -> 2
            R.id.dc_comics -> 3
            R.id.oscar_best_picture_1 -> 4
            R.id.avengers -> 5
            R.id.film_deja -> 6
            else -> 1
        }
    }


}