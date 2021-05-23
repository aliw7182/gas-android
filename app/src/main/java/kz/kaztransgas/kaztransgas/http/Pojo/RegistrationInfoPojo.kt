package kz.kaztransgas.kaztransgas.http.Pojo
import com.google.gson.annotations.SerializedName


data class  RegistrationInfoPojo(
    @SerializedName("address")
    val address: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("number")
    val number: String?
)