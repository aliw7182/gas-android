package kz.kaztransgas.kaztransgas.ui.RegistrationScreen.RegistrtionCodeScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kz.kaztransgas.kaztransgas.http.Pojo.CodePojo
import kz.kaztransgas.kaztransgas.http.RegistrationRepository
import kz.kaztransgas.kaztransgas.utils.BaseViewModel
import kz.kaztransgas.kaztransgas.utils.Event

class RegistrationCodeViewModel(val repository: RegistrationRepository):BaseViewModel() {

    private val _codeResponse = MutableLiveData<Event<CodePojo>>()
    val codeResponse:LiveData<Event<CodePojo>> = _codeResponse

    fun getCode(phone:String){
        requestWithLiveData(
            _codeResponse
        ){
            repository.verifyPhone(phone)
        }
    }
}