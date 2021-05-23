package kz.kaztransgas.kaztransgas.ui.LoginScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kz.kaztransgas.kaztransgas.http.Pojo.LoginPojo
import kz.kaztransgas.kaztransgas.http.RegistrationRepository
import kz.kaztransgas.kaztransgas.utils.BaseViewModel
import kz.kaztransgas.kaztransgas.utils.Event

class LoginViewModel(val repository: RegistrationRepository) : BaseViewModel() {
    private val _loginResponse = MutableLiveData<Event<LoginPojo>>()
    val loginResponse: LiveData<Event<LoginPojo>> = _loginResponse
    fun login(email: String,password:String){
        requestWithLiveData(
            _loginResponse
        ) {
            repository.login(email, password)
        }

    }
}