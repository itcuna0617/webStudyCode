package com.greedy.viewproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_layout)

        /* 버튼 생성 후 오랫동안 특정 뷰를 터치하고 있을 경우 발생하는 이벤트 처리 */
        val button = findViewById<Button>(R.id.button)
        button.setOnLongClickListener {
            Toast.makeText(this, "롱 클릭 이벤트 발생", Toast.LENGTH_LONG).show()

            true
        }

        val toggleButton = findViewById<ToggleButton>(R.id.toggle_button)
        toggleButton.setOnCheckedChangeListener { view, isChecked ->
//            Toast.makeText(this, "isChecked: ${isChecked}", Toast.LENGTH_SHORT).show()
            Log.d("toggle", "isChekced: ${isChecked}")
        }

        val editText = findViewById<EditText>(R.id.edit_text)
        editText.addTextChangedListener(object : TextWatcher {

            /* 매개변수는 알아보기 쉽게 수정 */
            /* 텍스트가 변경되기 바로 직전 호출되는 메소드(변경되기 직전의 문자열(s) 참조 가능) */
            override fun beforeTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            /* 텍스트 변경이 완료되는 시점에 호출되는 메소드(변경된 문자열(s) 참조 가능) */
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Toast.makeText(this@MainActivity, "${s.toString()}", Toast.LENGTH_SHORT).show()
                Log.d("onChange", "${s}로 변경중")
            }

            /* 텍스트가 변경된 직후 호출되는 메소드(변경된 텍스트 내용이 메소드로 전달되지는 않음) */
            override fun afterTextChanged(s: Editable?) {}
        })

        /* editText의 포커스 발생 및 해제 관련 */
        /*
            editText.requestFocus()      // 포커스를 줄 수 있다.
            editText.clearFocus()       // 포커스를 해제할 수 있다.
         */
        editText.setOnFocusChangeListener { view, hasFocus ->
            Toast.makeText(this, "포커스 변경: ${hasFocus}", Toast.LENGTH_SHORT).show()
        }

        /* checkBox 생성 이후 체크상태 변경 시 토스트 출력(이벤트가 발생한 객체: view, 상태값: isChecked) */
        val checkBox1 = findViewById<CheckBox>(R.id.checkbox1)
        checkBox1.setOnCheckedChangeListener { view, isChecked ->
            Toast.makeText(this, "${isChecked}, ${view.isChecked}", Toast.LENGTH_SHORT).show()

        }

        /* radioGroup에 이벤트를 달아서 어떤 라디오 버튼이 선택되었는지에 따라 토스트 출력 */
        val radioGroup = findViewById<RadioGroup>(R.id.radio_group)
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.radio_button1 -> Toast.makeText(this, "라디오 버튼 1 선택", Toast.LENGTH_SHORT).show()
                R.id.radio_button2 -> Toast.makeText(this, "라디오 버튼 2 선택", Toast.LENGTH_SHORT).show()
            }
        }

        /* Spinner 태그 완성해 보기(feat. adapter를 활용한 조립) */
        val spinner = findViewById<Spinner>(R.id.spinner)

        /* Spinner로 뿌려질 item들이 있는 배열을 리소스에 추가하고 오자. */

        /* 이 액티비티에서 사용 할 배열 값을 지닌 어댑터 생성 */
        /* android.R.layout.simple_spinner_item은 기본적으로 안드로이드가 스피너의 선택자를 표시하기 위해 제공해는 TextView 형태 */
        val adapter = ArrayAdapter.createFromResource(this, R.array.string_array, android.R.layout.simple_spinner_item)

        /* 드롭다운 메뉴로 표시하기 위한 레이아웃 식별자를 전달함(드롭다운 형태로 구성) */
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter

        /* Spinner 이벤트 추가 */
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            
            /* 특정 선택지가 선택된 직후에 호출되는 메소드 */
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                val item = parent?.getItemAtPosition(position).toString()
                Toast.makeText(this@MainActivity, "${position}, ${item}", Toast.LENGTH_SHORT).show()
            }

            /* 이미 선택한 선택지를 코드를 통해 삭제하거나 모든 선택지 내용을 삭제할 때(실행할 코드가 있다면) 호출됨 */
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
}







