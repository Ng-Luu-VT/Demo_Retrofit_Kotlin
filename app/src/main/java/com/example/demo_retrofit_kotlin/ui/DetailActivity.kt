package com.example.demo_retrofit_kotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.demo_retrofit_kotlin.DetailResponse
import com.example.demo_retrofit_kotlin.DetailService
import com.example.demo_retrofit_kotlin.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val edtID = findViewById<EditText>(R.id.edtID)
        val edtTitle = findViewById<EditText>(R.id.edtTitle)
        val edtBody = findViewById<EditText>(R.id.edtBody)
        val tvGetDetail = findViewById<TextView>(R.id.tvGetDetail)
        val tvResponseDelete = findViewById<TextView>(R.id.tvResponseDelete)
        val btnGetDetail = findViewById<Button>(R.id.btnGetDetail)
        val btnPostDetail = findViewById<Button>(R.id.btnPostDetail)
        val btnDeleteDetail = findViewById<Button>(R.id.btnDeleteDetail)
        btnGetDetail.setOnClickListener{getDetail(edtID,tvGetDetail)}
        btnPostDetail.setOnClickListener{postDetail(edtTitle,edtBody,tvGetDetail)}
        btnDeleteDetail.setOnClickListener{deteleDetail(tvResponseDelete)}
    }

    private fun deteleDetail(tvResponseDelete: TextView?) {
        val service = getRetrofitDetail()
        val call = service.deleteDetail(1)
        call.enqueue(object : Callback<DetailResponse>{
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                tvResponseDelete!!.text = response.message()
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                tvResponseDelete!!.text = t.message
            }

        })
    }

    private fun postDetail(
        edtTitle: EditText?,
        edtBody: EditText?,
        tvGetDetail: TextView?
    ) {
        val title: String = edtTitle!!.text.toString()
        val body: String = edtBody!!.text.toString()
        val service = getRetrofitDetail()
        val call = service.postDetail(title,body)
        val detail = DetailResponse()
        detail.body = body
        detail.title = title
        val call2 = service.postDetailBody(detail)
        call2.enqueue(object : Callback<DetailResponse>{
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                val detailResponse = response.body()!!
                val stringBuilder =
                    "ID: " + detailResponse.id!! +
                            "\n" +
                            "Title: " + detailResponse.title!! +
                            "\n" +
                            "Detail: " + detailResponse.body!!
                tvGetDetail!!.text = stringBuilder
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                tvGetDetail!!.text = t.message
            }

        })
//        call.enqueue(object : Callback<DetailResponse>{
//            override fun onResponse(
//                call: Call<DetailResponse>,
//                response: Response<DetailResponse>
//            ) {
//                val detailResponse = response.body()!!
//                val stringBuilder =
//                        "ID: " + detailResponse.id!! +
//                        "\n" +
//                        "Title: " + detailResponse.title!! +
//                        "\n" +
//                        "Detail: " + detailResponse.body!!
//                tvGetDetail!!.text = stringBuilder
//            }
//
//            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
//                tvGetDetail!!.text = t.message
//            }
//
//        })
    }

    private fun getDetail(edtID: EditText?, tvGetDetail: TextView?) {
        if (edtID!!.text.toString() != ""){
            val id: Int = edtID.text.toString().toInt()
            val service = getRetrofitDetail()
            val call = service.getListDetail(id)
            call.enqueue(object : Callback<DetailResponse>{
                override fun onResponse(
                    call: Call<DetailResponse>,
                    response: Response<DetailResponse>
                ) {
                    val detailResponse = response.body()!!
                    val stringBuilder =
                            "ID: " + detailResponse.id!! +
                            "\n" +
                            "Title: " + detailResponse.title!! +
                            "\n" +
                            "Detail: " + detailResponse.body!!
                    tvGetDetail!!.text = stringBuilder
                }

                override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                    tvGetDetail!!.text = t.message
                }

            })
        }
        else Toast.makeText(applicationContext,"Cần nhập ID" , Toast.LENGTH_SHORT).show()
    }

    private fun getRetrofitDetail(): DetailService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(DetailService::class.java)
    }

    companion object {
        var BaseUrl = "https://jsonplaceholder.typicode.com/"
    }
}