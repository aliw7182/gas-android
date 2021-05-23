package kz.kaztransgas.kaztransgas.ui.RegistrationScreen

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import kz.kaztransgas.kaztransgas.R
import kz.kaztransgas.kaztransgas.databinding.RegistrationScreenBinding
import kz.kaztransgas.kaztransgas.ui.RegistrationScreen.RegistrationInfoScreen.RegistrationInfoScreenData
import kz.kaztransgas.kaztransgas.utils.AbstractFragment
import kz.kaztransgas.kaztransgas.utils.FragmentNavigator
import kz.kaztransgas.kaztransgas.utils.FragmentReference

class RegistrationScreen : AbstractFragment() {

    private var _binding: RegistrationScreenBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RegistrationScreenBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.input.inputType = InputType.TYPE_CLASS_NUMBER
        binding.continueBtn.isEnabled = false
        binding.input.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.inputTitle.visibility = View.VISIBLE
                binding.title.text = getString(R.string.registration_user)
                if(s != null && s.length > 11){
                    binding.continueBtn.isEnabled = true
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
        binding.continueBtn.setOnClickListener {
            fragmentNavigator
                .navigateTo(FragmentReference.REGISTRATION_INFO_SCREEN)
                .isAdd()
                .setParcelable(RegistrationInfoScreenData(binding.input.text.toString()))
                .setCustomAnimations(FragmentNavigator.RIGHT_TO_LEFT_ANIMATION)
                .navigate()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}