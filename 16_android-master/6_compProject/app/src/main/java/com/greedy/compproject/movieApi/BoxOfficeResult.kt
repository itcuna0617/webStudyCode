package com.greedy.compproject.movieApi

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/* data class2 */
data class BoxOfficeResult (
    @SerializedName("dailyBoxOfficeList")
    var dailyBoxOfficeList: List<Movie> = listOf(),
    @SerializedName("showRange")
    var showRange: String
) : Serializable{
}