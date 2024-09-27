package com.greedy.logcatandtoastproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Error가 가장 우선 순위 및 심각성이 높고 Verbose가 가장 낮다. */
        /* 첫 번째 인자로 메시지의 종류를 구분(태그이름)하고 두 번째 인자로 실제 출력 할 메시지를 전달한다. */
        Log.v("myTag1", "Verbose Message")      // 일반적인 메시지를 출력
        Log.d("myTag2", "Debug Message")        // 디버깅용 메시지를 출력
        Log.i("myTag3", "Info Message")         // 정보 표기용 메시지를 출력
        Log.w("myTag4", "Warning Message")      // 경고 메시지를 출력
        Log.e("myTag5", "Error Message")        // 에러 발생 메시지를 출력

        /* 토스트 객체 생성 */
        /* LENGTH_SHORT: 대략 2초, LENGTH_LONG: 대략 3초 */
//        Toast.makeText(this, "Toast Message", Toast.LENGTH_LONG).show()

        var toast: Toast = Toast.makeText(this, "Toast Message", Toast.LENGTH_LONG)

        toast.setGravity(Gravity.TOP, 200, 0)       // 약간 오른쪽으로 200만큼 빗겨남
//        toast.setGravity(Gravity.TOP or Gravity.RIGHT, 0, 0)   // TOP이면서 RIGHT인 위치에 생성 됨

        toast.show()
    }
}