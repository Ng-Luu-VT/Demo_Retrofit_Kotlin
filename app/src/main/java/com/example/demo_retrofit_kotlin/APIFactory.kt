package com.example.demo_retrofit_kotlin

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class APIFactory() {
    private var retrofit: Retrofit? = null
    var baseURL = "https://jsonplaceholder.typicode.com/"

    private fun getRETOFIT() : Retrofit{
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }
        return retrofit!!
    }

    val userApi : UserApi = getRETOFIT().create(UserApi::class.java)
}