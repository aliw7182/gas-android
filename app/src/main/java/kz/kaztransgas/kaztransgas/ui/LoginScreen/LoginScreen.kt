package kz.kaztransgas.kaztransgas.ui.LoginScreen

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kz.kaztransgas.kaztransgas.activity.ProfileActivity
import kz.kaztransgas.kaztransgas.databinding.LoginScreenBinding
import kz.kaztransgas.kaztransgas.http.Pojo.LoginPojo
import kz.kaztransgas.kaztransgas.utils.AbstractFragment
import kz.kaztransgas.kaztransgas.utils.SpHelper
import kz.kaztransgas.kaztransgas.utils.Status
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class LoginScreen : AbstractFragment() {
    private var _binding: LoginScreenBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<LoginViewModel>()
    private val spHelper by inject<SpHelper>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoginScreenBinding.inflate(inflater, container, false)
        val view = binding.root

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spHelper.apiToken = null

        binding.emailInput.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS

        binding.continueBtn.isEnabled = true
        binding.continueBtn.setOnClickListener {
            viewModel.login(binding.emailInput.text.toString(), binding.passInput.text.toString())
            val intent = Intent(context, ProfileActivity::class.java)
            startActivity(intent)
            viewModel.loginResponse.observe(viewLifecycleOwner, {
                when (it.status) {
                    Status.SUCCESS -> {
                        Log.d("success", it.data.toString())
                        if (it.data is LoginPojo) {
                            val response = it.data as LoginPojo
                            spHelper.apiToken = response.token
                        }
                    }

                    Status.LOADING -> {

                    }

                    Status.ERROR -> {
                        Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
                    }
                }

            })

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}