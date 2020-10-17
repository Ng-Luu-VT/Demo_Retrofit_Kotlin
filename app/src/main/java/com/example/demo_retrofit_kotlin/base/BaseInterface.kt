package com.example.demo_retrofit_kotlin.base

import com.example.demo_retrofit_kotlin.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BaseInterface {
    @GET("users/{id}")
    fun getUser1(@Path("id") id:Int
    ) : Call<UserResponse>
    @GET("users")
    fun getListUser() : Call<MutableList<UserResponse>>
}