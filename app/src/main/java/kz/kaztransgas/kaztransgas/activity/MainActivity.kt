package kz.kaztransgas.kaztransgas.activity

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import kz.kaztransgas.kaztransgas.BuildConfig
import kz.kaztransgas.kaztransgas.R
import kz.kaztransgas.kaztransgas.utils.FragmentReference

class MainActivity : AbstractActivity() {

    private var mExit = false
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Log.d("screenContainer",screenContainer.toString())
        fragmentNavigator
            .navigateTo(FragmentReference.WELCOME_SCREEN)
            .isRoot(true)
            .navigate()

    }

    override val screenContainer: Int
        get() = R.id.container



    private fun displayBackStack(fm: FragmentManager) {
        val count = fm.backStackEntryCount
        Log.d(TAG, "There are $count entries")
        for (i in 0 until count) {
            val name = fm.getBackStackEntryAt(i).name
            Log.d(TAG, "entry $i: $name")
        }
    }
}