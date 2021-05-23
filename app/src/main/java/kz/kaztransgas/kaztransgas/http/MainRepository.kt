package kz.kaztransgas.kaztransgas.http

import retrofit2.Retrofit

class MainRepository(val retrofit: Retrofit) {
    private val service:Api
    init {
        service = retrofit.create(Api::class.java)
    }

    suspend fun getMainContent(){

    }

}
