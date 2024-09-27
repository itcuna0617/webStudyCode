package com.greedy.lifecycleproject

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        Log.d("myTag", "onCreate")
//    }
//
//    override fun onStart() {
//        super.onStart()
//        Log.d("myTag", "onStart")
//    }
//
//    override fun onResume() {
//        super.onResume()
//        Log.d("myTag", "onResume")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.d("myTag", "onStop")
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d("myTag", "onDestroy")
//    }
//
//    override fun onRestart() {
//        super.onRestart()
//        Log.d("myTag", "onRestart")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.d("myTag", "onPause")
//    }

    /* 어떤 라이프 사이클 상에서도 해당 변수가 존재 하도록 전역변수 설정 */
    /* TextView에 저장될 값을 지닌 변수이자 현재 화면에 보이는 숫자를 저장하고 있기도 함 */
    private var num = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.save_to_restore)

        var numberTextView = findViewById<TextView>(R.id.number)
        var increaseButtonView = findViewById<Button>(R.id.increase)
        var numberEditView = findViewById<EditText>(R.id.number_edit)
        var setButtonView = findViewById<Button>(R.id.set_number)

        increaseButtonView.setOnClickListener {
            num = numberTextView.text.toString().toInt() + 1
            numberTextView.text = num.toString()
        }

        setButtonView.setOnClickListener {
            num = numberEditView.text.toString().toInt()
            numberTextView.text = num.toString()
        }

    }

    /*
        Bundle은 버퍼처럼 임시 저장 공간
        (바뀔 값을 저장할 공간이자 기존 창에서 불러올 값을 지닌 공간)
        (Session이나 request 같은 느낌)
     */
    /* onStop 메소드 호출 이후에 호출 됨(액티비티가 비활성화 되는 시점에 호출 됨) */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        /* Bundle에 보존하고자 하는 값(데이터)를 저장하는 코드 작성 */
        outState.putInt("num", num)
    }

    /* onStart 메소드 호출 직후에 호출 됨(액티비티가 활성화 되는 시점에 호출) */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        /* 전달받은 Bundle 객체에서 값(데이터)를 반환받아 상태 복구를 위해 속성값을 초기화 */
        num = savedInstanceState.getInt("num")

        var numberText = findViewById<TextView>(R.id.number)
        numberText.text = num.toString()
    }

}