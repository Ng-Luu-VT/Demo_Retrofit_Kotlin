package com.example.demo_retrofit_kotlin

import com.google.gson.annotations.SerializedName

class UserResponse {
    @SerializedName("id")
    var id: Int? = null


    @SerializedName("name")
    var name: String? = null


    @SerializedName("username")
    var username: String? = null


    @SerializedName("email")
    var email: String? = null


    @SerializedName("address")
    var address: Address? = null


    @SerializedName("phone")
    var phone: String? = null

    @SerializedName("website")
    var website: String? = null

    @SerializedName("company")
    var company: Company? = null
}

class Address {
    @SerializedName("street")
    var street: String? = null

    @SerializedName("suite")
    var suite: String? = null


    @SerializedName("city")
    var city: String? = null


    @SerializedName("zipcode")
    var zipcode: String? = null


    @SerializedName("geo")
    var geo: Geo? = null
}

class Company {
    @SerializedName("name")
    var name: String? = null

    @SerializedName("catchPhrase")
    var catchPhrase: String? = null

    @SerializedName("bs")
    var bs: String? = null
}

class Geo {
    @SerializedName("lat")
    var lat: String? = null


    @SerializedName("lng")
    var lng: String? = null
}

data class ListUserResponse(
    val results: List<UserResponse>
    )