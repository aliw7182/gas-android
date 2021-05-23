package kz.kaztransgas.kaztransgas.utils

import com.google.gson.Gson
import kz.kaztransgas.kaztransgas.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitBuilder(private val spHelper: SpHelper) {
    private val builder: OkHttpClient.Builder
    private val BASE_URL = "http://130.61.58.200/api/"
    fun addInterceptor(interceptor: Interceptor) {
        builder.addInterceptor(interceptor)
    }

    fun addLoggerInterceptor() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        builder.addInterceptor(interceptor)
    }

    fun build(): Retrofit {
//        val headerInterceptor = Interceptor { chain ->
//            val api_session: String? = spHelper.apiToken
//            var request = chain.request()
//            val newUrl = request.url.toString()
//            request =
//                if (api_session == null || api_session.isEmpty() || api_session.trim { it <= ' ' }
//                        .isEmpty()) {
//                        request.newBuilder()
//                        .url(newUrl)
//                        .build()
//                } else {
//                    request.newBuilder()
//                        .url(newUrl)
//                        .addHeader("X-CSRFToken", api_session)
//                        .build()
//                }
//            val response = chain.proceed(request)
//            if (response.isSuccessful) {
//                val response_api_session = response.header("Api-Session")
//                if (response_api_session != null && !response_api_session.isEmpty()) {
//                    spHelper.apiToken = response_api_session
//                }
//            }
//            response
//        }
//        builder.addInterceptor(addLoggerInterceptor())
        if (BuildConfig.BUILD_TYPE.equals("stage") || BuildConfig.BUILD_TYPE == "debug") {
            addLoggerInterceptor()
        }
        builder.readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
        if (spHelper.apiToken != null) {
            builder.addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request = chain.request().newBuilder()
                        .addHeader("Authorization", "JWT ${spHelper.apiToken}").build()
                    return chain.proceed(request)
                }
            })
        }
        val client: OkHttpClient = builder.build()
        val apiUrl: String
        apiUrl = BASE_URL
        return Retrofit.Builder()
            .baseUrl(apiUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    init {
        builder = OkHttpClient.Builder()
    }
}
