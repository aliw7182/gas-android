package kz.kaztransgas.kaztransgas.ui.welcomeScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kz.kaztransgas.kaztransgas.databinding.WelcomeScreenBinding
import kz.kaztransgas.kaztransgas.utils.AbstractFragment
import kz.kaztransgas.kaztransgas.utils.FragmentNavigator
import kz.kaztransgas.kaztransgas.utils.FragmentReference

class WelcomeScreen :AbstractFragment(){


    private var _binding: WelcomeScreenBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = WelcomeScreenBinding.inflate(inflater, container, false)
        val view = binding.root
        return view    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerBtn.setOnClickListener {
            fragmentNavigator
                .navigateTo(FragmentReference.REGISTRATION_SCREEN)
                .setCustomAnimations(FragmentNavigator.RIGHT_TO_LEFT_ANIMATION)
                .isAdd()
                .navigate()
        }

        binding.login.setOnClickListener {
            fragmentNavigator
                .navigateTo(FragmentReference.LOGIN_SCREEN)
                .setCustomAnimations(FragmentNavigator.RIGHT_TO_LEFT_ANIMATION)
                .isAdd()
                .navigate()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
