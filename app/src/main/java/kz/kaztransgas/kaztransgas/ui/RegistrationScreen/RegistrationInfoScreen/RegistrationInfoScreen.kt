package kz.kaztransgas.kaztransgas.ui.RegistrationScreen.RegistrationInfoScreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kz.kaztransgas.kaztransgas.databinding.RegistrationInfoScreenBinding
import kz.kaztransgas.kaztransgas.http.Pojo.RegistrationInfoPojo
import kz.kaztransgas.kaztransgas.utils.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class RegistrationInfoScreen : AbstractFragment() {

    private var _binding: RegistrationInfoScreenBinding? = null

    private val binding get() = _binding!!

    private val spHelper by inject<SpHelper>()


    private val viewModel by viewModel<RegistrationInfoViewModel>()


    lateinit var data: RegistrationInfoScreenData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getParcelable<RegistrationInfoScreenData>(BundleReference.DATA)?.let {
                data = it
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RegistrationInfoScreenBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getInfo(data.accountNumber)
        viewModel.infoResponse.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    Log.d("success", it.data.toString())
                    if (it.data is RegistrationInfoPojo) {
                        val response = it.data as RegistrationInfoPojo
                        binding.addressInput.setText(response.address)
                        binding.accountNumberInput.setText(response.number)
                        binding.cityInput.setText(response.city)
                        binding.nameInput.setText(response.fullName)
                        spHelper.accountNumber = data.accountNumber
                    }
                }

                Status.LOADING -> {

                }

                Status.ERROR -> {
                    Toast.makeText(context,it.error,Toast.LENGTH_LONG).show()
                }
            }

        })

        binding.continueBtn.setOnClickListener {
            fragmentNavigator
                .navigateTo(FragmentReference.REGISTRATION_PHONE_SCREEN)
                .isAdd()
                .setCustomAnimations(FragmentNavigator.RIGHT_TO_LEFT_ANIMATION)
                .navigate()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}