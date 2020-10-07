package com.example.demo_retrofit_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.item_recycler_view.*
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
        val edtUserID = findViewById<EditText>(R.id.edtUserID)
        val edtTitle = findViewById<EditText>(R.id.edtTitle)
        val edtBody = findViewById<EditText>(R.id.edtBody)
        val tvGetDetail = findViewById<TextView>(R.id.tvGetDetail)
        val btnGetDetail = findViewById<Button>(R.id.btnGetDetail)
        val btnPostDetail = findViewById<Button>(R.id.btnPostDetail)
        btnGetDetail.setOnClickListener{getDetail(edtID,tvGetDetail)}
        btnPostDetail.setOnClickListener{postDetail(edtUserID,edtTitle,edtBody,tvGetDetail)}
    }

    private fun postDetail(
        edtUserID: EditText?,
        edtTitle: EditText?,
        edtBody: EditText?,
        tvGetDetail: TextView?
    ) {

        val userID: Int = edtUserID!!.text.toString().toInt()
        val title: String = edtTitle!!.text.toString()
        val body: String = edtBody!!.text.toString()
        val service = getRetrofitDetail()
        val detail : DetailResponse
        val call = service.postDetail(userID,title,body)
        call.enqueue(object : Callback<DetailResponse>{
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                val detailResponse = response.body()!!
                val stringBuilder = "UserID: " + detailResponse.userId!! +
                        "\n" +
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
                    val stringBuilder = "UserID: " + detailResponse.userId!! +
                            "\n" +
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