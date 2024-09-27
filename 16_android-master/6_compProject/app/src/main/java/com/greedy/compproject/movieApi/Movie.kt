package com.greedy.compproject.movieApi

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/* 나중에 JSON 문자열을 파싱해서 값을 저장할 데이터 클래스를 만들 때 속성들은 모두 String?으로 하자. */
//data class Movie(var title: String, var hours: String)

/*
    retrofit 이후 data class의 변화
    json 문자열에서 원하는 값이 원하는 속성으로 주입되도록 @SerializedName을 사용하고 이후 intent를 통해
    객체를 직렬화 해서 넘겨야 되기 때문에 Serializable도 상속 받자.
 */
/* data class1 */
data class Movie(
                 @SerializedName("movieNm")
                 var movieNm: String?,
                 @SerializedName("rankInten")
                 var rankInten: String?,
                 @SerializedName("rank")
                 var rank: String?
                ) : Serializable {

}