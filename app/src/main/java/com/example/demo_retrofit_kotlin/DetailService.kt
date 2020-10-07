package com.example.demo_retrofit_kotlin

import retrofit2.Call
import retrofit2.http.*


interface DetailService {
    @GET("posts/{id}")
    fun getListDetail(@Path("id") id: Int
    ) : Call<DetailResponse>
    @POST("posts")
    @FormUrlEncoded
    fun postDetail(@Field("userID") userID: Int,
                    @Field("title") title: String,
                    @Field("body") body: String) : Call<DetailResponse>
    @POST("posts")
    fun postDetailBody(@Body detailResponse: DetailResponse) : Call<DetailResponse>
}