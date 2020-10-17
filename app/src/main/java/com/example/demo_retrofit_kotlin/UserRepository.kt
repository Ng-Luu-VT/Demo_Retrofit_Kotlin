package com.example.demo_retrofit_kotlin

import com.example.demo_retrofit_kotlin.base.BaseRepository


class UserRepository (private val userApi: UserApi) : BaseRepository(){


     suspend fun getListUser() : MutableList<UserResponse>? {
        val userResponse = safeApiCall(
            call = {userApi.getListUser()},
            errorMessage = "Error Fetching User Data"
        )
        return userResponse?.results?.toMutableList()
    }
}