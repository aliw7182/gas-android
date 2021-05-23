package kz.kaztransgas.kaztransgas.utils

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import kz.kaztransgas.kaztransgas.activity.AbstractActivity
import org.koin.android.ext.android.inject

abstract  class AbstractFragment :Fragment(){

    val navigator by inject<FragmentNavigator>()


     val fragmentNavigator: FragmentNavigator.FragmentNavigatorBuilder
         get() {
             return navigator.fragmentNavigator(activity as AbstractActivity)
         }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }




}
