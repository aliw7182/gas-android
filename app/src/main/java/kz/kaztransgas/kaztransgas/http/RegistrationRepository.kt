package kz.kaztransgas.kaztransgas.http

import android.provider.ContactsContract
import kz.kaztransgas.kaztransgas.http.Pojo.CodePojo
import kz.kaztransgas.kaztransgas.http.Pojo.LoginPojo
import retrofit2.Response
import retrofit2.Retrofit

class RegistrationRepository(val retrofit: Retrofit) {
    private val service:Api
    init {
        service = retrofit.create(Api::class.java)
    }

    suspend fun getRegistrationInfo(number:String) = service.getRegistrationInfo(number)

    suspend fun verifyPhone(phone:String):Response<CodePojo>{
        val param:HashMap<String,Any> = HashMap()
        param["mobile_phone"] = phone

        return service.verifyPhone(param)
    }

    suspend fun login(email:String,pass:String) :Response<LoginPojo>{
        val param:HashMap<String,Any> = HashMap()
        param["mobile_phone"] = email
        param["password"] = pass
        return service.login(param)
    }
}