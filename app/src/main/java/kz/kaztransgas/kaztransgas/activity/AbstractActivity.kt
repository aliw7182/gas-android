package kz.kaztransgas.kaztransgas.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import kz.kaztransgas.kaztransgas.utils.ActivityMap
import kz.kaztransgas.kaztransgas.utils.FragmentMap
import kz.kaztransgas.kaztransgas.utils.FragmentNavigator
import kz.kaztransgas.kaztransgas.utils.SpHelper
import org.koin.android.ext.android.inject

abstract class AbstractActivity :AppCompatActivity() {

    val internalFragmentNavigator by inject<FragmentNavigator>()

    val fragmentMap by inject<FragmentMap>()

    val activityMap by inject<ActivityMap>()

    val fragmentNavigator:FragmentNavigator.FragmentNavigatorBuilder
        get() {
            return internalFragmentNavigator.fragmentNavigator(this)
        }

    val spHelper by inject<SpHelper>()


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }
    open fun setCurrentTab(position: Int) {}


    open fun getTabIndex(fragment: Any?): Int {
        return -1
    }


    @get:IdRes
    open val screenContainer: Int
        get() = 0

}