package kz.kaztransgas.kaztransgas.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_profile.*
import kz.kaztransgas.kaztransgas.R
import kz.kaztransgas.kaztransgas.ui.ScannerScreen.AccountBottomSheet
import kz.kaztransgas.kaztransgas.ui.ScannerScreen.AccountBottomSheetListener
import kz.kaztransgas.kaztransgas.utils.FragmentReference

class ProfileActivity : AbstractActivity(),BottomNavigationView.OnNavigationItemSelectedListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

            fragmentNavigator
                .navigateTo(FragmentReference.MAIN_SCREEN)
                .isRoot(true)
                .navigate()
    }


    override val screenContainer: Int
        get() = R.id.container

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.home ->{
                fragmentNavigator
                    .navigateTo(FragmentReference.MAIN_SCREEN)
                    .isRoot(true)
                    .navigate()
                return true
            }
            R.id.profile ->{
                fragmentNavigator
                    .navigateTo(FragmentReference.PROFILE_SCREEN)
                    .isRoot(true)
                    .navigate()
                return true

            }
            R.id.settings ->{
                fragmentNavigator
                    .navigateTo(FragmentReference.SETTINGS_SCREEN)
                    .isRoot(true)
                    .navigate()
                return true
            }
            R.id.check ->{
                fragmentNavigator
                    .navigateTo(FragmentReference.HISTORY_SCREEN)
                    .isRoot(true)
                    .navigate()
                return true
            }
            else -> return false

        }

    }
}