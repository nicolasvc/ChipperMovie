package com.example.chippermovie.common.viewmvc

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes

open class BaseViewMvc<LISTENER_TYPE>(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    @LayoutRes private val layoutId: Int
) {
    protected val listeners = HashSet<LISTENER_TYPE>()

    val rootView: View = layoutInflater.inflate(layoutId,parent,false)

    protected val context: Context get() = rootView.context

    fun registerListener(listenerType: LISTENER_TYPE){
        listeners.add(listenerType)
    }

    fun removeListener(listenerType: LISTENER_TYPE){
        listeners.remove(listenerType)
    }

    protected fun <T:View?> findViewById(@IdRes id:Int):T = rootView.findViewById<T>(id)


}

