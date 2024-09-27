package com.greedy.compproject.movieApi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json")
    fun getMovieList(
        @Query("targetDt") targetDt: String,
        @Query("key") key: String
    ): Call<MovieResponse>
}











