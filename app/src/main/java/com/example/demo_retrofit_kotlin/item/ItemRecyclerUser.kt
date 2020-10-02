package com.example.demo_retrofit_kotlin.item

class ItemRecyclerUser{
    private var tvTitle: String? = null
    private var tvDetail: String? = null

    fun getTvTitle(): String? {
        return tvTitle
    }

    fun setTvTitle(tvTitle: String?) {
        this.tvTitle = tvTitle
    }

    fun getTvDetail(): String? {
        return tvDetail
    }

    fun setTvDetail(tvDetail: String?) {
        this.tvDetail = tvDetail
    }

}