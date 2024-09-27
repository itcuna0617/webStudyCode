package com.greedy.firstproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        setContentView(R.layout.my_layout)      // 우리가 만든 my_layout 화면이 그려지게 설정

        /* button에 이벤트 핸들러 추가 */
        var btn: Button = findViewById(R.id.btn1)
//        btn.setOnClickListener(object : View.OnClickListener{
//            override fun onClick(v: View?) {
//                var btnText = (v as Button).text.toString()
//
//                /* applicationContext: 현재 MainActivity를 뜻함(현재의 떠 있는 창) */
//                Toast.makeText(this@MainActivity, "버튼 이름: $btnText", Toast.LENGTH_LONG).show()
//            }
//        })

        /* EditText View를 id로 불러와서 담기 */
        var editTextView: EditText = findViewById(R.id.editText)

        /*
            View.OnclickListener 인터페이스는 정의해야 할(오버라이딩 할) 추상메소드가 하나 뿐이고 이런 인터페이스는
            함수형 인터페이스로서 람다식을 적용할 수 있다.(주로 클릭 시 많이 사용)
         */
        btn.setOnClickListener { v ->
            var btnText = (v as Button).text.toString()
            Toast.makeText(this, "버튼 이름: $btnText".toLowerCase(), Toast.LENGTH_SHORT).show()

            /* EditText 뷰의 경우는 text 값에 직접 값을 넣을 때 직접 setter를 호출하고 넘겨줘야 된다. */
            editTextView.setText("버튼 클릭했지롱~")
        }

        /*
           인자도 하나면(매개변수가 하나면) 인자 이름과 관련된 코드도 생략할 수 있고 대명사(it)을 통해 전달받은 인자에
           접근할 수 있다.
        */
        btn.setOnClickListener { 
            var btnText = (it as Button).text.toString()
            Toast.makeText(applicationContext, "버튼 이름: $btnText".toLowerCase(), Toast.LENGTH_SHORT).show()

            /* EditText 뷰의 경우는 text 값에 직접 값을 넣을 때 직접 setter를 호출하고 넘겨줘야 된다. */
            editTextView.setText("버튼 클릭했지롱~")
        }
    }

}








