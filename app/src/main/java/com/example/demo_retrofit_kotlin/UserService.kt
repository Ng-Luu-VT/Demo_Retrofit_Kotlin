package com.example.demo_retrofit_kotlin

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService{
    @GET("users/{id}")
    fun getUser1(@Path("id") id:Int
    ) : Call<UserResponse>

}
