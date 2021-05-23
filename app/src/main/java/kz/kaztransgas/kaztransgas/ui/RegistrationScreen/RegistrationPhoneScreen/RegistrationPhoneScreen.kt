package kz.kaztransgas.kaztransgas.ui.RegistrationScreen.RegistrationPhoneScreen

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import kz.kaztransgas.kaztransgas.databinding.RegistrationPhoneScreenBinding
import kz.kaztransgas.kaztransgas.ui.RegistrationScreen.RegistrtionCodeScreen.RegistrationCodeScreenData
import kz.kaztransgas.kaztransgas.utils.AbstractFragment
import kz.kaztransgas.kaztransgas.utils.FragmentNavigator
import kz.kaztransgas.kaztransgas.utils.FragmentReference

class RegistrationPhoneScreen : AbstractFragment() {

    private var _binding: RegistrationPhoneScreenBinding? = null
    private val binding get() = _binding!!
    private var phoneNumber = "+7"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RegistrationPhoneScreenBinding.inflate(inflater, container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.phoneInput.inputType = InputType.TYPE_CLASS_PHONE
        binding.phoneInput.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                phoneNumber+=s
            }

        })

        binding.continueBtn.setOnClickListener {
            fragmentNavigator
                .navigateTo(FragmentReference.REGISTRATION_CODE_SCREEN)
                .isAdd()
                .setParcelable(RegistrationCodeScreenData(phoneNumber))
                .setCustomAnimations(FragmentNavigator.RIGHT_TO_LEFT_ANIMATION)
                .navigate()

        }
    }

    override fun onResume() {
        super.onResume()
        activity?.let {
            it.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            it.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}