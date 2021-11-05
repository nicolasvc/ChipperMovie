package com.example.chippermovie.common.screens.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.chippermovie.MyApplication
import com.example.chippermovie.di.activity.ActivityModule

open class BaseActivity :AppCompatActivity(){

    private val appCompositionRoot get() = (application as MyApplication).appComponent

    private val activityCompositionRoot by lazy{
        appCompositionRoot.newActivityComponentBuilder()
            .activity(this)
            .activityModule(ActivityModule)
            .build()

    }

    private val presentationComponent by lazy {
        activityCompositionRoot.newPresentationComponent()
    }

    protected val injector get() = presentationComponent

}