package com.greedy.compproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.greedy.compproject.movieApi.Movie

class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_list_activity)

//        val message = intent.getStringExtra("message")
//        Toast.makeText(this, "현재 전달된 $message", Toast.LENGTH_SHORT).show()

        /* 1. 리스트에 포함 될 샘플 데이터 */
        /* movie 샘플(영화제목과 상영시간만 포함) */
//        val movies: List<Movie> = listOf(
//                                        Movie("영화1", "1시간")
//                                      , Movie("영화2", "2시간")
//                                      , Movie("영화3", "3시간")
//        )
                                // 조회만 할 예정이라 immutable list를 만듦
                                // (수정이 가능하게 할려면 mutableListOf()를 써서 mutablie list를 만들자)

        /* 넘어온 bundle 추출 */
        val bundle = intent.extras

        /* 재료1 추출(랭킹 타이틀에 해당하는 TextView에 대입할 재료) */
        val showRange = bundle!!.getString("showRange")
        val titleView = findViewById<TextView>(R.id.rankTitle)
        titleView.text = showRange!!.substringBefore('~') + " 기준"

        /* 재료2 추출(어댑터에게 던져질 재료) */
        val movies: List<Movie> = bundle!!.getSerializable("movieList") as List<Movie>  // 개별 뷰 수정
        Log.d("movies", movies.toString())

        /* 3. 각 항목이 표시 될 각각의 개별 뷰가 표시될 방식을 관리 할 레이아웃 매니저 */
        /*
            LinearLayoutManager: 객체를 사용하면 목록 형태로 데이터를 표시하는 레이아웃 매니저
            GridLayoutManager: 객체를 사용하면 격자 형태로 데이터를 표시하는 레이아웃 매니저
         */
        val layoutManager = LinearLayoutManager(this)   // 레이아웃 매니저 객체 생성

//        val movies: List<Movie> = listOf()
        val adapter = MovieAdapter(movies)                     // 데이터를 넘겨준 어댑터 객체 생성

        /* recyclerView를 활용하기 위한 재료 활용 */
        val recyclerView = findViewById<RecyclerView>(R.id.movie_list)
        recyclerView.setHasFixedSize(false)             // 부착 될 뷰들의 높이가 다를 수 있으면 false를 준다.(높이가 같으면 true를 전달해서 최적화를 유도할 수 있다.)
        recyclerView.layoutManager = layoutManager      // RecyclerView에 필요한 레이아웃 매니저 객체 설정
        recyclerView.adapter = adapter                  // RecyclerView에 필요한 어댑터 객체 설정

        /* 메인으로 돌아가는 버튼에 이벤트 추가 */
        val mainButtonView = findViewById<android.widget.Button>(R.id.mainBtn)
        mainButtonView.setOnClickListener {
            
            /* 메인(MovieActivity)으로 돌아가도록 명시적 인텐트로 Activity 전환 */
            val intent = Intent(this@MovieListActivity, MovieActivity::class.java)
            startActivity(intent)
        }
    }
}