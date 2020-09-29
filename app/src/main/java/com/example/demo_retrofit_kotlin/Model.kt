package com.example.demo_retrofit_kotlin


object Model{
    data class Result(val query: Query)
    data class Query(val searchInfo: SearchInfo)
    data class SearchInfo(val totalhits: Int)
}