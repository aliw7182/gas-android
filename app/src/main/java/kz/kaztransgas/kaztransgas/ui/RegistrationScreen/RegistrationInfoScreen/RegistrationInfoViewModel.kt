package kz.kaztransgas.kaztransgas.ui.RegistrationScreen.RegistrationInfoScreen

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import kz.kaztransgas.kaztransgas.http.Pojo.RegistrationInfoPojo
import kz.kaztransgas.kaztransgas.http.RegistrationRepository
import kz.kaztransgas.kaztransgas.utils.BaseViewModel
import kz.kaztransgas.kaztransgas.utils.Event
import kz.kaztransgas.kaztransgas.utils.Resource
import retrofit2.Response
import java.io.IOException

class RegistrationInfoViewModel(private val registrationRepository: RegistrationRepository) :BaseViewModel() {

    private val _infoResponse = MutableLiveData<Event<RegistrationInfoPojo>>()
    val infoResponse: LiveData<Event<RegistrationInfoPojo>> = _infoResponse

    fun getInfo(accountNumber:String){
        requestWithLiveData(
            _infoResponse,
        ){
            registrationRepository.getRegistrationInfo(accountNumber)
        }
    }


}
