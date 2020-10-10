package com.example.demo_retrofit_kotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
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
        val btnClick = findViewById<View>(R.id.button)
        val btnClickNext = findViewById<View>(R.id.buttonNext)
        btnClick.setOnClickListener{getCurrentData(tvUserData)}
        btnClickNext.setOnClickListener{switchFragment()}
        setUpRecyclerViewUser()
    }

    private fun switchFragment() {
        val intent = Intent(this, DetailActivity::class.java)
        startActivity(intent)
    }

    private fun getRetrofitUser(): UserService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(UserService::class.java)
    }

    private fun getCurrentData(tvUserData: TextView?) {
        val service = getRetrofitUser()
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

    }

    private fun setUpRecyclerViewUser() {
        val rvTest = findViewById<RecyclerView>(R.id.rvTest)
        val mRecyclerUserAdapter = RecyclerUserAdapter(genData())
        if (mRecyclerUserAdapter.data!!.size == 0){
            Toast.makeText(applicationContext,"Load List Faile!!! " ,Toast.LENGTH_SHORT).show()
        }
        else{
            rvTest.layoutManager = LinearLayoutManager(this)
            rvTest.setHasFixedSize(true)
            //rvTest.addItemDecoration(DividerItemDecoration(rvTest.context, DividerItemDecoration.VERTICAL))
            rvTest.adapter = mRecyclerUserAdapter
        }

    }

    private fun genData(): ArrayList<ItemRecyclerUser>? {
        val listItem: ArrayList<ItemRecyclerUser> = ArrayList<ItemRecyclerUser>()
        val service = getRetrofitUser()
        val call = service.getListUser()
        call.enqueue(object : Callback<MutableList<UserResponse>>{
            override fun onResponse(
                call: Call<MutableList<UserResponse>>,
                response: Response<MutableList<UserResponse>>
            ) {
                val listUser = response.body()
                if (listUser != null){
                    for (i in 0..listUser.size){
                        val item = ItemRecyclerUser()
                        item.setTvTitle("ID: " + listUser[i].id!!)
                        item.setTvDetail("Name: " + listUser[i].name!!)
                        listItem.add(item)
                    }
                }

            }

            override fun onFailure(call: Call<MutableList<UserResponse>>, t: Throwable) {
                Toast.makeText(applicationContext,"Faile!!! " + t.message,Toast.LENGTH_SHORT).show()
            }


        })
        return listItem
    }

    companion object {

        var BaseUrl = "https://jsonplaceholder.typicode.com/"
        var id = 1
    }
}