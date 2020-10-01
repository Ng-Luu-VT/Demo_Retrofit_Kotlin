package com.example.demo_retrofit_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        val tvUserData = findViewById<TextView>(R.id.textview)
        findViewById<View>(R.id.button).setOnClickListener{getCurrentData(tvUserData)}
    }

    private fun getCurrentData(tvUserData: TextView?) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(UserService::class.java)
        val call = service.getUser1(id)
        call.enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                val userResponse = response.body()!!
                val stringBuilder = "ID: " + userResponse.id!! +
                        "\n"+
                        "Name: "+ userResponse.username!!
                tvUserData!!.text = stringBuilder
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                tvUserData!!.text = t.message
            }

        })
    }
    companion object {

        var BaseUrl = "https://jsonplaceholder.typicode.com/"
        var id = 1
    }
}