package com.example.mobileprogramming_homework2

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_operation_bmi.*
import org.jetbrains.anko.toast

class OperationBmiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operation_bmi)
        setTitle("BMI")

        opertionBtn.setOnClickListener{
            val height = height_editText.text.toString()
            val weight = weigth_editText.text.toString()

            if(height.length != 3){
                toast("신장의 길이를 다시 입력하세요.")
                height_editText.setText("")
            }else{
                if(weight.length > 300){
                    toast("체중을 다시 입력하세요.\n\r 범위: 0~300")
                    weigth_editText.setText("")
                }else{
                    val inner_height = Integer.parseInt(height)
                    val inner_weight = Integer.parseInt(weight)
                    val result = inner_weight / Math.pow(inner_height/100.0,2.0)//비만도 계산
                    showImageView(result)
                }
            }



        }

    }
    fun showImageView(result:Double){
        result_layout.visibility=View.VISIBLE
        when{//비만도에 따른 메시지 표시
            result>=34 -> resultText.text="고도비만"
            result>=25 -> resultText.text="과체중"
            result>=18.5 -> resultText.text="정상"
            else -> resultText.text="저체중"

        }
        when{//비만도에 따른 그림 표시
            result >=23 -> imageView.setImageResource(R.drawable.ic_baseline_sentiment_very_dissatisfied_24)//과체중
            result >=18.5 -> imageView.setImageResource(R.drawable.ic_baseline_sentiment_very_satisfied_24)//정상
            else -> imageView.setImageResource(R.drawable.ic_baseline_sentiment_satisfied_24)//저체중

        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val focusView: View? = currentFocus
        if (focusView != null) {
            val rect = Rect()
            focusView.getGlobalVisibleRect(rect)
            val x = ev!!.x.toInt()
            val y = ev.y.toInt()
            if (!rect.contains(x, y)) {
                val imm =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm?.hideSoftInputFromWindow(focusView.getWindowToken(), 0)
                focusView.clearFocus()
            }
        }
        return super.dispatchTouchEvent(ev)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.operationmenu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent: Intent
        when(item.itemId){
            R.id.logout_Btn2->{//로그아웃
                intent = Intent(this,LoginActivity::class.java)
                finishAffinity()
                startActivity(intent)
                return true
            }

            R.id.action_home2->{ //홈화면으로 이동하기
                intent = Intent(this,HomeActivity::class.java)
                finishAffinity()
                startActivity(intent)
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }
}