package kz.kaztransgas.kaztransgas.http

import kz.kaztransgas.kaztransgas.http.Pojo.CodePojo
import kz.kaztransgas.kaztransgas.http.Pojo.LoginPojo
import kz.kaztransgas.kaztransgas.http.Pojo.RegistrationInfoPojo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {
    @GET("auth/account_number/{number}")
    suspend fun getRegistrationInfo(@Path("number") number:String):Response<RegistrationInfoPojo>
    @POST("auth/otp/send/")
    suspend fun verifyPhone(@Body param:Map<String,Any>):Response<CodePojo>
    @POST("auth/")
    @JvmSuppressWildcards
    suspend fun login(@Body param:Map<String,Any>):Response<LoginPojo>

}