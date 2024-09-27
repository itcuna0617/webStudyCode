package com.greedy.compproject.movieApi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    var baseUrl: String = "http://www.kobis.or.kr"
    var api: MovieAPI

    init {
        val retrofit = Retrofit.Builder()
                      .baseUrl(baseUrl)
                      .addConverterFactory(GsonConverterFactory.create())
                      .build()

        api = retrofit.create(MovieAPI::class.java)
    }
}