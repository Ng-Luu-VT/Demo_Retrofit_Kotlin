package com.example.demo_retrofit_kotlin

import com.google.gson.annotations.SerializedName



class DetailResponse {
    @SerializedName("userId")
    var userId: Int? = null

    @SerializedName("id")
    var id: Int? = null

    @SerializedName("title")
    var title : String? =null

    @SerializedName("body")
    var body : String? =null
}