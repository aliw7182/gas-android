package kz.kaztransgas.kaztransgas.utils

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.annotation.AnimRes
import androidx.fragment.app.FragmentManager
import kz.kaztransgas.kaztransgas.R
import kz.kaztransgas.kaztransgas.activity.AbstractActivity
import kz.kaztransgas.kaztransgas.ui.RegistrationScreen.RegistrtionCodeScreen.RegistrationCodeScreenData
import java.lang.IllegalStateException

class FragmentNavigator(private val fragmentMap: FragmentMap, private val context: Context) {

    private val TAG = "GlobalNavigator"

    fun fragmentNavigator(activity: AbstractActivity): FragmentNavigatorBuilder {
        return FragmentNavigatorBuilder(activity)
    }

    inner class FragmentNavigatorBuilder(private val activity: AbstractActivity) {

        private var destinationTo: String = ""

        private @AnimRes
        var customAnimation: Int? = null
        private var mDataParcelable: Parcelable? = null
        private var isRoot: Boolean = false
        private var mBundle: Bundle? = null
        private var customAnimations: IntArray? = null
        private var isAdd: Boolean = true

        fun isReplace() = apply {
            isAdd = false
        }

        fun isAdd() = apply {
            isAdd = true
        }

        fun setCustomAnimations(customAnimations: IntArray) = apply {
            this.customAnimations = customAnimations
        }


        fun setCustomAnimation(customAnimation: Int?) = apply {
            this.customAnimation = customAnimation
        }

        fun navigateTo(destination: String) = apply {
            this.destinationTo = destination
        }

        fun isRoot(isRoot: Boolean) = apply {
            this.isRoot = isRoot
        }

        fun setBundle(bundle: Bundle) = apply {
            this.mBundle = bundle
        }

        fun setParcelable(parcelable: Parcelable) = apply {
            this.mDataParcelable = parcelable
        }

        fun setStandardNavigation() = apply {
            this.setCustomAnimations(RIGHT_TO_LEFT_ANIMATION_RETURN)
            this.isAdd()
        }

        private fun getFragmentClass(destinationTo: String): AbstractFragment? {
            val fragmentClass = fragmentMap.get(destinationTo)
            if (fragmentClass != null) {
                val fragmentInstance = fragmentClass.newInstance()
                if (fragmentInstance is AbstractFragment) {
                    return fragmentInstance
                }
            }
            return null
        }

        fun navigate() {
            try {
                val screen = getFragmentClass(destinationTo)
                if (screen != null) {
                    if (mBundle != null) {
                        screen.arguments = mBundle
                    } else {
                        val args = Bundle()
                        if (mDataParcelable != null) {
                            args.putParcelable(BundleReference.DATA, mDataParcelable)
                            screen.arguments = args
                        }
                    }
                    setScreen(screen)
                } else {
                    Log.e(TAG, "Fragment not found in FragmentMap")
                }
            } catch (e: java.lang.InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        }

        private fun setScreen(screen: AbstractFragment) {
            val tag = screen.javaClass.name
            val fragmentManager = activity.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

            if (customAnimation != null) {
                fragmentTransaction.setCustomAnimations(customAnimation!!, 0)
            }

            customAnimations?.let {
                if (it.size == 2) {
                    fragmentTransaction.setCustomAnimations(it[0], it[1])
                }
                if (it.size == 4) {
                    fragmentTransaction.setCustomAnimations(it[0], it[1], it[2], it[3])
                }
            }

            if (isAdd) {
                fragmentTransaction.add(activity.screenContainer, screen, tag)
            } else {
                fragmentTransaction.replace(activity.screenContainer, screen, tag)
            }

            if (isRoot) {

                fragmentManager.popBackStackImmediate(
                    null,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
                )
                fragmentManager.fragments.forEach {
                    it.onDestroy()
                }
                fragmentTransaction.commit()

                val index = activity.getTabIndex(screen.javaClass)
                if (index >= 0) {
                    try {
                        activity.setCurrentTab(index)
                    } catch (e: IllegalStateException) {
                        e.printStackTrace()
                    }
                }
            } else {
                fragmentTransaction
                    .addToBackStack(tag)
                    .commit()
            }
            customAnimation = null
            mDataParcelable = null
            isRoot = false
            mBundle = null
            customAnimations = null
            isAdd = true
        }
    }

    companion object {
        val RIGHT_TO_LEFT_ANIMATION = intArrayOf(R.anim.slid_in_right, R.anim.slide_out_to_left)
        val RIGHT_TO_LEFT_ANIMATION_RETURN = intArrayOf(R.anim.slid_in_right, 0, 0, R.anim.slide_out_to_left)
        val BOTTOM_TO_TOP_ANIMATION = intArrayOf(R.anim.slide_in_up, 0, 0, R.anim.slide_out_bottom)
    }

}