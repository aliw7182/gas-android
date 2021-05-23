package kz.kaztransgas.kaztransgas.http.Pojo

import com.google.gson.annotations.SerializedName

data class LoginPojo(
    @SerializedName("access")
    val token:String
)
