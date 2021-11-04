package com.example.chippermovie.common.screens

import androidx.appcompat.app.AppCompatActivity
import com.example.chippermovie.MyApplication

open class BaseActivity :AppCompatActivity(){

    private val appCompositionRoot get() = (application as MyApplication).appComponent


    private val presentationComponent by lazy {
        appCompositionRoot.newPresentationComponent()
    }

    protected val injector get() = presentationComponent

}