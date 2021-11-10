package com.example.chippermovie.common.screens.fragments

import androidx.fragment.app.Fragment
import com.example.chippermovie.common.screens.activities.BaseActivity

open class BaseFragment : Fragment(){


    private val presentationComponent by lazy {
        (requireActivity() as BaseActivity).activityCompositionRoot.newPresentationComponent()
    }

    protected val injector get() = presentationComponent



}