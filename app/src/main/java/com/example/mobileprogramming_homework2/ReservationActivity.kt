package com.example.mobileprogramming_homework2

import android.content.Intent
import android.net.Uri
import android.os.SystemClock
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_reservation.*
import org.jetbrains.anko.toast
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class ReservationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        var calendar_msg:String =""
        var time_msg:String =""
        var state:Boolean = true

        startBtn.setOnClickListener{
            if(state){
                toast("예약시작 버튼이 활성화 되었습니다.")
                radioGroup.visibility = View.VISIBLE //radio 그룹
                resultLayout.visibility = View.VISIBLE // 결과 레이아웃
                chrono.start()
                chrono.base = SystemClock.elapsedRealtime()
                state=false
            }else{
                state = true
                chrono.stop() // 예약종료 버튼을 누르지 않고 예약 시작버튼을 누를시
                toast("상태가 초기화 되었습니다.")
                chrono.base = SystemClock.elapsedRealtime()
                radioGroup.visibility = View.GONE //radio그룹
                dayBtn.isChecked = false// 날짜 설정 버튼
                timeBtn.isChecked = false//시간 설정 버튼
                framLayout.visibility = View.GONE //캘린더
                timePicker.visibility = View.GONE //timeplck
                resultTextView.setText("")//결과 메시지 초기화
                resultLayout.visibility = View.GONE// 결과 레이아웃
            }

        }

        radioGroup.setOnCheckedChangeListener{radioGroup, i ->
            when(i){
                R.id.dayBtn -> {
                    toast("날짜 설정 버튼을 누르셨습니다.\"")
                    framLayout.visibility = View.VISIBLE //캘린더
                    timePicker.visibility = View.GONE //timePicker

                }

                R.id.timeBtn -> {
                    toast("시간 설정 버튼을 누르셨습니다.")
                    framLayout.visibility = View.GONE //캘린더
                    timePicker.visibility = View.VISIBLE //timePicker
                }
            }

        }


        calendarView?.setOnDateChangeListener{view, year, month, dayOfMonth ->
            calendar_msg = year.toString()+"년" + (month+1).toString()+"월" + dayOfMonth.toString()+"일"


        }
        timePicker.setOnTimeChangedListener{view, hour, min ->
            time_msg = hour.toString()+"시" + min.toString()+"분"

        }
        commitBtn.setOnClickListener{
            toast("예약 완료 버튼을 누르셨습니다.")
            resultTextView.setText(calendar_msg + time_msg +"예약됨")
            chrono.stop()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.reservationmenu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent: Intent
        when(item.itemId){
            R.id.logout_Btn1->{//로그아웃
                intent = Intent(this,LoginActivity::class.java)
                finishAffinity()
                startActivity(intent)
                return true
            }

            R.id.action_home->{ //홈화면으로 이동하기
                intent = Intent(this,HomeActivity::class.java)
                finishAffinity()
                startActivity(intent)
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }
}