package com.example.demo_retrofit_kotlin

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface UserApi{
    @GET("users/{id}")
    fun getUser1(@Path("id") id:Int
    ) : Response<UserResponse>
    @GET("users")
    fun getListUser() : Response<ListUserResponse>
}
