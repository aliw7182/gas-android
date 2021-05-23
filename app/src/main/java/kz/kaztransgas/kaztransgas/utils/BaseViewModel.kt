package kz.kaztransgas.kaztransgas.utils

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

abstract class BaseViewModel : ViewModel() {



    fun <L> requestWithLiveData(
        liveData: MutableLiveData<Event<L>>,
        request: suspend () -> Response<L>
    ) {

        liveData.postValue(Event.loading())

        this.viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = request.invoke()
                Log.d("response",response.body().toString())
                Log.d("response_message",response.message().toString())

                if (response.body() != null) {
                    liveData.postValue(Event.success(response.body()))
                } else if (response.errorBody()!= null) {
                    liveData.postValue(Event.error(response.errorBody().toString()))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                liveData.postValue(Event.error(null))
            }
        }
    }

    fun <T> requestWithCallback(
        request: suspend () -> Response<T>,
        response: (Event<T>) -> Unit) {

        response(Event.loading())

        this.viewModelScope.launch(Dispatchers.IO) {
            try {
                val res = request.invoke()

                launch(Dispatchers.Main) {
                    if (res.body() != null) {
                        response(Event.success(res.body()))
                    } else if (res.errorBody() != null) {
                        response(Event.error(res.errorBody().toString()))
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                launch(Dispatchers.Main) {
                    response(Event.error(null))
                }
            }
        }
    }
}