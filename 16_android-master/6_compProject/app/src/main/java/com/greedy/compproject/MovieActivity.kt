package com.greedy.compproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.greedy.compproject.movieApi.Movie
import com.greedy.compproject.movieApi.MovieResponse
import com.greedy.compproject.movieApi.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.text.SimpleDateFormat
import java.util.*

/*
    RecyclerView를 통한 항목을 표시하기 위한 준비
    1. 리스트에 포함 될 데이터(API로 얻어온 JSON 데이터 -> 파싱)
    2. 리스트에 포함 될 개별 데이터를 표시할 XML 레이아웃 파일
    3. 리스트에 포함 될 각 항목이 표시될 방식을 관리할 레이아웃 매니저
    4. 리스트에 포함 될 각 데이터를 보여줄 뷰 객체를 보관할 뷰 홀더 클래스
    5. 리스트에 포함 될 리스트 데이터와 레이아웃 파일을 연결하는 등 총체적인 관리 기능을 제공할 어댑터 클래스
 */
class MovieActivity : AppCompatActivity() {

    /* public static이자 singletone에 해당되는 동반자 객체에 KEY를 상수로 설정 */
    companion object {
        const val KEY = "268b5e7dfae2bb42042fad2cb202833c"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_main_activity)

        /* 영화 보기 Button 뷰 객체 반환 받아 이벤트 추가 */
        val movieListButton = findViewById<Button>(R.id.movie_list_btn)
        movieListButton.setOnClickListener {

            /* EditText 추가 이후 */
            val beforeDayCount: Int? = findViewById<EditText>(R.id.beforeDayCount).text.toString().toIntOrNull()    // toString()을 써서 Editable -> String으로 반환 받자

            /* 페이지를 이동해서 리스트를 화면에 뿌릴 예정(RecyclerView가 있는 페이지로 이동) */

            /* 명시적 인텐트(어떤 액티비티로 이동할지 정해서 값을 전달)를 활용한 페이지 이동 */
            
            /* 인텐트 객체를 생성하며 Context 객체(this)와 MovieListActivity 클래스의 정보를 전달 */
//            val intent = Intent(this, MovieListActivity::class.java)
            
            /* 목록 액티비티 화면에서 토스트 메시지로 활용 될 메세지를 추가 */
//            intent.putExtra("message", "영화 리스트")
            
            /* startActivity 메소드를 호출하여 인텐트의 목적지로 이동하며 값을 전달 */
//            startActivity(intent)

//            getResult("20221228")

            if (beforeDayCount != null) {                       // 사용자가 보고 싶은 날을 숫자로 입력 했다면

                /* 버튼을 누르면 어제 날짜로 데이터를 받아오게 만들자. */
                val cal: Calendar = Calendar.getInstance()      // 시스템의 현재시간
                cal.add(Calendar.HOUR, 9)                       // 현재 한국 시간 적용을 위해 그리니치 천문대 시간 + 9를 적용
                cal.add(Calendar.DAY_OF_MONTH, -beforeDayCount)              // 하루 전 Calendar형 날짜
                val sdf = SimpleDateFormat("yyyyMMdd")    // 4자리 년도, 2자리 월, 2자리 일로 포맷 설정
                val targetDt = sdf.format(cal.time)             // 해당 포맷의 String형으로 반환

//            val sdf2 = SimpleDateFormat("yyyyMMdd hh:mm:ss")
//            Log.d("sdf",sdf2.format(cal.time))

                getResult(targetDt)
            } else {                                            // 입력한 값이 숫자가 아니거나 입력하지 않았다면
                var toast = Toast.makeText(this@MovieActivity, "숫자인 날짜를 입력해 주세요!!", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            }
        }
    }

    fun getResult(targetDt: String) {
        RetrofitBuilder.api
            .getMovieList(targetDt, KEY)
            .enqueue(object : Callback<MovieResponse> {

                /* Rest 방식의 비동기 통신 성공 시 */
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {

                    /* MovieResponse 타입 객체 안에 모든 json 문자열 관련 내용이 객체와 속성들에 담겨져 넘어옴을 확인할 수 있다. */
//                    val movieResponse = response.body()
//                    Log.d("success", movieResponse.toString())

                    val movieResponse = response.body()

                    /* 재료1. 랭킹 누적 기간 */
                    val showRange = movieResponse!!.boxofficeResult.showRange
                    Log.d("success", showRange)

                    /* 재료2. 화면에 그려낼 영화들 */
                    val movieList: List<Movie> = movieResponse!!.boxofficeResult.dailyBoxOfficeList
                    Log.d("success", movieList.toString())

                    /* 위에서 뽑은 재료를 담을 Bundle을 구성하고 객체는 직렬화하고 기본 자료형은 그대로 담는다. */
                    val bundle = Bundle()
                    bundle.putString("showRange", showRange)        // 재료 1 담기
                    bundle.putSerializable(                         // 재료 2 담기
                        "movieList",
                        (movieList as java.io.Serializable)
                    )

                    /* 명시적 인텐트 생성 */
                    val intent: Intent = Intent(this@MovieActivity, MovieListActivity::class.java)
                    intent.putExtras(bundle)

                    startActivity(intent)                           // 해당 activity로 bundle을 가지고 이동
                }

                /* Rest 방식의 비동기 통신 실패 시 */
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Toast.makeText(this@MovieActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }
}