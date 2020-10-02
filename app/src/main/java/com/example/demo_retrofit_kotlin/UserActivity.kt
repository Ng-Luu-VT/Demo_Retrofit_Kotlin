package com.example.demo_retrofit_kotlin

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demo_retrofit_kotlin.adapter.RecyclerUserAdapter
import com.example.demo_retrofit_kotlin.item.ItemRecyclerUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

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
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                val userResponse = response.body()!!
                val stringBuilder = "ID: " + userResponse.id!! +
                        "\n" +
                        "Name: " + userResponse.username!!
                tvUserData!!.text = stringBuilder
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                tvUserData!!.text = t.message
            }

        })
        setUpRecyclerViewUser()
    }

    private fun setUpRecyclerViewUser() {
        val rvTest = findViewById<RecyclerView>(R.id.rvTest)
        val mRecyclerUserAdapter = RecyclerUserAdapter(genData())
        rvTest.layoutManager = LinearLayoutManager(this)
        rvTest.setHasFixedSize(true)
        rvTest.adapter = mRecyclerUserAdapter
    }

    private fun genData(): ArrayList<ItemRecyclerUser>? {
        val listItem: ArrayList<ItemRecyclerUser> = ArrayList<ItemRecyclerUser>()
        for (i in 0..10) {
            val item = ItemRecyclerUser()
            item.setTvTitle("" + (i + 1))
            item.setTvDetail("" + (i + 1))
            listItem.add(item)
        }

        return listItem
    }

    companion object {

        var BaseUrl = "https://jsonplaceholder.typicode.com/"
        var id = 1
    }
}