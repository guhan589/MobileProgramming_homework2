package com.example.mobileprogramming_homework2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_find_id_passwd.*
import org.jetbrains.anko.toast

class FindlicenseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_id_passwd)

        var preference:Preference_id
        var state:Boolean = true
        find_idBtn.setOnClickListener{
            state= true
            changeView(state)
        }

        find_passwdBtn.setOnClickListener{
            state=false
            changeView(state)
        }
        send_btn.setOnClickListener{
            if(state){
                val name = id_editText.text.toString()
                if(name.length>0)
                    view_id(name)
                else
                    toast("이름을 입력하세요.")
            }else{
                val id = passwd_editText.text.toString()
                if(id.length>0)
                    view_pw(id)
                else
                    toast("아이디를 입력하세요.")
            }
        }

    }
    fun changeView(state:Boolean) {
        if (state){//아이디찾기
            passwd_editText.setText("")
            find_id_layout.visibility = View.VISIBLE
            find_passwd_layout.visibility = View.GONE
            send_btn.setText("아이디찾기")
        }

        else {//비밀번호찾기
            id_editText.setText("")
            find_id_layout.visibility = View.GONE
            find_passwd_layout.visibility = View.VISIBLE
            send_btn.setText("비밀번호 찾기")
        }
        result_textView.setText("")
    }

    fun view_id( name:String){
        val preferenceName = Preference_name(applicationContext,name)
        val id = preferenceName.getString(name)

        Log.d("TAG", "view_id: "+id)
        result_textView.setText("당신의 아이디는 $id 입니다.")
    }
    fun view_pw(id:String){
        val preferenceId = Preference_id(applicationContext,id)
        val passwd = preferenceId.getString("password")
        Log.d("TAG", "view_passwd: "+passwd)

        result_textView.setText("당신의 비밀번호는 $passwd 입니다.")
    }
}