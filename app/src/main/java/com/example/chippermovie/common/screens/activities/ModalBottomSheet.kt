package com.example.chippermovie.common.screens.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chippermovie.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

open class ModalBottomSheet : BottomSheetDialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_bottom_sheet, container, false)
    }
}