package com.example.chippermovie.common.screens.dialog

import androidx.fragment.app.DialogFragment
import com.example.chippermovie.common.screens.activities.BaseActivity

open class BaseDialog : DialogFragment() {

    private val presentationComponent by lazy {
        (requireActivity() as BaseActivity).activityCompositionRoot.newPresentationComponent()

    }

    protected val injector get() = presentationComponent

}