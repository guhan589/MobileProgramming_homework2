package com.example.mobileprogramming_homework2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.toast

class HomeActivity : AppCompatActivity() {
    private var backPressedTime: Long = 0
    private val FINISH_INTERVAL_TIME: Long = 2000
    private lateinit var intent1:Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setTitle("메인")


        reservationBtn.setOnClickListener{//예약버튼
            intent1 = Intent(this,ReservationActivity::class.java)
            startActivity(intent1)
        }
        bmiBtn.setOnClickListener{//관리자에게 연락하기
            intent1 = Intent(this,OperationBmiActivity::class.java)
            startActivity(intent1)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.homemenu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent:Intent
        when(item.itemId){
            R.id.logout_Btn->{//로그아웃
                finish()
                return true
            }

            R.id.call_Btn1->{//관리자에게 전화하기
                intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:010-5257-4549")
                if(intent.resolveActivity(packageManager)!=null)//다이얼 사용이 가능한지 확인
                    startActivity(intent)
                else
                    toast("다이얼에 연결할 수 없습니다.")
                return true
            }


        }
        return super.onOptionsItemSelected(item)
    }
    override fun onBackPressed() {
        val tempTime = System.currentTimeMillis()
        val intervalTime: Long = tempTime - backPressedTime

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            super.onBackPressed()
        } else {
            backPressedTime = tempTime
            Toast.makeText(applicationContext, "한번 더 뒤로가기 버튼을 누르시면 종료됩니다.", Toast.LENGTH_SHORT)
                .show()
        }
    }
}