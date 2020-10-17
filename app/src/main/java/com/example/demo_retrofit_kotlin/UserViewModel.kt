package com.example.demo_retrofit_kotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.cancel
import retrofit2.create

class UserViewModel() : ViewModel(){
    private val userLiveData : MutableLiveData<MutableList<UserResponse>?> by lazy { MutableLiveData<MutableList<UserResponse>?>()}

    private val userRepository : UserRepository = UserRepository(APIFactory().userApi)
     suspend fun getListUser(){
        val listUser = userRepository.getListUser()
        userLiveData.postValue(listUser)
    }
    fun cancelAllRequests() = coroutineContext.cancel()
}