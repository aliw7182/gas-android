package kz.kaztransgas.kaztransgas.ui.MainScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kz.kaztransgas.kaztransgas.databinding.MainScreenBinding
import kz.kaztransgas.kaztransgas.utils.AbstractFragment
import kz.kaztransgas.kaztransgas.utils.FragmentNavigator
import kz.kaztransgas.kaztransgas.utils.FragmentReference

class MainScreen :AbstractFragment() {

    private var _binding:MainScreenBinding? = null
    private val binding get() = _binding!!
    private var number:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getString("number").let {
                number = it
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainScreenBinding.inflate(inflater,container,false)
        val view  = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.scan.setOnClickListener {
            fragmentNavigator.navigateTo(FragmentReference.SCAN_SCREEN)
                .setCustomAnimations(FragmentNavigator.RIGHT_TO_LEFT_ANIMATION_RETURN)
                .isAdd()
                .navigate()
        }

        number?.let {
            binding.gasNumberInput.setText(it.toString())
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}