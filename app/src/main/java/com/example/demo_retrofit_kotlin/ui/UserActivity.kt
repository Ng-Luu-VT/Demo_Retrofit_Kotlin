package com.example.demo_retrofit_kotlin.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demo_retrofit_kotlin.R
import com.example.demo_retrofit_kotlin.UserApi
import com.example.demo_retrofit_kotlin.UserViewModel
import com.example.demo_retrofit_kotlin.adapter.RecyclerUserAdapter
import com.example.demo_retrofit_kotlin.item.ItemRecyclerUser
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

@Suppress("DEPRECATION")
class UserActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel

    private val listItem: ArrayList<ItemRecyclerUser> = ArrayList<ItemRecyclerUser>()
    var userAdapter : RecyclerUserAdapter?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        val tvUserData = findViewById<TextView>(R.id.textview)
        val btnClick = findViewById<Button>(R.id.button)
        val btnClickNext = findViewById<Button>(R.id.buttonNext)
        //btnClick.setOnClickListener{getCurrentData(tvUserData)}
        btnClickNext.setOnClickListener{switchFragment()}
        setUpRecyclerViewUser()
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)


        userViewModel


    }

     suspend fun getData(){
        userViewModel.getListUser()
    }

    private fun switchFragment() {
        val intent = Intent(this, DetailActivity::class.java)
        startActivity(intent)
    }

    private fun getRetrofitUser(): UserApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(UserApi::class.java)
    }

//    private fun getCurrentData(tvUserData: TextView?) {
//        val api : UserApi = APIFactory(BaseUrl).getRETOFIT()!!.create(UserApi::class.java)
//        val call = api.getUser1(id)
//        call.enqueue(object : Callback<UserResponse> {
//            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
//                val userResponse = response.body()!!
//                val stringBuilder = "ID: " + userResponse.id!! +
//                        "\n" +
//                        "Name: " + userResponse.username!!
//                tvUserData!!.text = stringBuilder
//            }
//
//            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
//                tvUserData!!.text = t.message
//            }
//
//        })
//
//    }

    private fun setUpRecyclerViewUser() {
        val rvTest = findViewById<RecyclerView>(R.id.rvTest)
         userAdapter = RecyclerUserAdapter(listItem)
        rvTest.layoutManager = LinearLayoutManager(this)
        rvTest.setHasFixedSize(true)
            //rvTest.addItemDecoration(DividerItemDecoration(rvTest.context, DividerItemDecoration.VERTICAL))
        rvTest.adapter = userAdapter
    }

//    private fun getData() {
//        val service = getRetrofitUser()
//        val call = service.getListUser()
//        call.enqueue(object : Callback<MutableList<UserResponse>>{
//            override fun onResponse(
//                call: Call<MutableList<UserResponse>>,
//                response: Response<MutableList<UserResponse>>
//            ) {
//                val listUser = response.body()
//                if (listUser != null){
//                    listUser.forEach { iitem->
//                        val item = ItemRecyclerUser()
//                        item.id = ("ID: " + iitem.id!!)
//                        item.title = ("Name: " + iitem.name!!)
//                        listItem.add(item)
//                    }
//                    userAdapter?.notifyDataSetChanged()
//                    //update
//                    // data n UI owr day ne
//                }
//
//            }
//
//            override fun onFailure(call: Call<MutableList<UserResponse>>, t: Throwable) {
//                Toast.makeText(applicationContext,"Faile!!! " + t.message,Toast.LENGTH_SHORT).show()
//            }
//        })
//    }

    companion object {

        var BaseUrl = "https://jsonplaceholder.typicode.com/"
        var id = 1
    }
}