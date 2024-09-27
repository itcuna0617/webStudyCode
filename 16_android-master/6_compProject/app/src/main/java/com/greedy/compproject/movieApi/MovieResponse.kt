package com.greedy.compproject.movieApi

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/* data class3 */
data class MovieResponse (
    @SerializedName("boxOfficeResult")
    var boxofficeResult: BoxOfficeResult
) : Serializable