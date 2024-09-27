package com.greedy.compproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.greedy.compproject.movieApi.Movie

/* 4번과 5번에 해당되는 뷰 홀더를 포함한 어댑터 만들기 */
/* 어댑터 */
/*
    주어진 데이터 갯수만큼 개별 뷰를 생성하고 해당 개별 뷰들에 필요한 시점에 데이터를 하나씩 작성하는 것에 대한
    설정 클래스

    RecyclerView의 내부객체인 Adapter 클래스를 상속받는 클래스로 일반적으로 클래스 내부에 정의 한 뷰 홀더(MovieItemViewHolder)
    클래스의 타입도 함께 지정해야 하므로 제네릭 타입으로 MovieAdapter.MovieItemViewHolder를 작성한다.
    (같은 뷰홀더 타입을 지닌 어댑터를 상속받아 정의 한다고 생각하자.)
 */
class MovieAdapter(private val dataList: List<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieItemViewHolder>() {

    /* 1. */
    /* 목록에 포함 될 전체 데이터 항목의 갯수를 반환한다. */
    override fun getItemCount(): Int {
        return dataList.size
    }

    /* 2. */
    /* 항목을 보여줄 레이아웃(개별 뷰용 XML파일)을 반환한다. */
    override fun getItemViewType(position: Int) = R.layout.movie_list_item

    /* 3. */
    /* 2번에서 반환한 XML 파일을 inflate를 통해 객체로 만들어(메모리에 인지) 내부 클래스인 ViewHolder에게 넘김 */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {

        /*
            inflate 함수의 세가지 인자
            첫번째(viewType): 레이아웃 리소스의 식별자(2번에서 방금 만든 개별 뷰 xml 파일의 번호)
            두번째(parent): inflate 메소드의 실행 결과로 생성 될 뷰가 부착될 부모 뷰(뷰그룹, RecyclerView)
            세번째(false): 코드를 실행하는 시점에 부모 뷰에 뷰 객체를 부착해야 하는지 여부
                          (RecyclerView의 경우 뷰 내부에서 자체적으로 뷰의 부착을 진행하므로 반드시 false로 주고
                           parameter로만 던져 주는 개념으로 정의해야 한다.)
         */
        var view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)

        return MovieItemViewHolder(view)
    }

    /* 4. */
    /* 뷰 홀더 */
    /*
        목록에 표시할 각 항목을 보여줄 뷰 객체와 뷰의 구성 요소를 제공받기 위해 사용할 뷰 홀더 클래스를 내부 클래스로 정의한다.
        개별 뷰 객체를 생성자로 전달하도록 주 생성자를 정의한다.(3번의 반환값이 전달됨)
        (개별 뷰의 구성 요소를 파악하고 데이터의 내용을 작성하는 것에 대한 정의라고 생각하자)
     */
    class MovieItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var movie: Movie

//        val title = view.findViewById<TextView>(R.id.movie_title)
//        val hours = view.findViewById<TextView>(R.id.movie_hour)

        val rankView = view.findViewById<TextView>(R.id.movie_rank)
        val movieNmView = view.findViewById<TextView>(R.id.movie_movieNm)
        val arrowView = view.findViewById<ImageView>(R.id.arrow_rankInten)      // 화살표 관련 내용 추가 후 작성
        val rankIntenView = view.findViewById<TextView>(R.id.movie_rankInten)

        /* 데이터가 넘어오면 개별뷰의 해당 요소에 적용되도록 bind 함수를 정의 */
       fun bind(m: Movie) {
            this.movie = m
            
//            title.text = movie.title
//            hours.text = movie.hours

              rankView.text = movie.rank
              movieNmView.text = movie.movieNm

              val rankInten = movie.rankInten       // 변동 수치 추출

              if(rankInten!!.toInt() < 0) {         // 내려간 변동 수치라면(음수라면)
                 arrowView.setImageResource(R.drawable.ic_sort_down_solid)
              }

              rankIntenView.text = rankInten
        }
    }

    /* 5. */
    /* 4번에서 정의 된 bind 함수를 활용해서 개별 항목 데이터를 개별 뷰에 작성, 스크롤을 내릴 때 자동 호출  */
    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {

        holder.bind(dataList[position])
    }
}