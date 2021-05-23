package kz.kaztransgas.kaztransgas.ui.RegistrationScreen.RegistrtionCodeScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import kz.kaztransgas.kaztransgas.databinding.RegistrationCodeScreenBinding
import kz.kaztransgas.kaztransgas.utils.AbstractFragment
import kz.kaztransgas.kaztransgas.utils.BundleReference
import org.koin.android.ext.android.inject

class RegistrationCodeScreen : AbstractFragment() {

    private var _binding: RegistrationCodeScreenBinding? = null

    private val binding get() = _binding!!

    lateinit var data: RegistrationCodeScreenData

    private val viewModel by inject<RegistrationCodeViewModel>()

    private var code = "1111"
    private var inputCode = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getParcelable<RegistrationCodeScreenData>(BundleReference.DATA)?.let {
                data = it
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RegistrationCodeScreenBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel.getCode(data.phoneNumber)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.firstNumber.requestFocus()
        binding.firstNumber.addTextChangedListener{
            it?.let {
                if(it.isNotEmpty()){
                    binding.secondNumber.requestFocus()
                    inputCode+=it.toString()
                }
            }
        }
        binding.secondNumber.addTextChangedListener{
            it?.let {
                if(it.isNotEmpty()){
                    binding.thirdNumber.requestFocus()
                    inputCode+=it.toString()
                }
            }
        }
        binding.thirdNumber.addTextChangedListener{
            it?.let {
                if(it.isNotEmpty()){
                    binding.fourthNumber.requestFocus()
                    inputCode+=it.toString()
                }
            }
        }
        binding.fourthNumber.addTextChangedListener{
            it?.let {
                if(it.isNotEmpty()){
                    inputCode+=it.toString()
                    if(inputCode == code){

                    }
                    else{
                        binding.errorText.visibility = View.VISIBLE
                    }
                }
            }
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
