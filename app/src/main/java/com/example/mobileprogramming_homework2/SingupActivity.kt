package com.example.mobileprogramming_homework2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_singup.*
import org.jetbrains.anko.toast
import kotlin.concurrent.timer

class SingupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singup)

        var isState:Boolean = false;

        singup_Btn.setOnClickListener{
            val id = singup_id.text.toString()
            val passwd = singup_passwd.text.toString()
            val name = singup_name.text.toString()

            if (!id.equals("")&&!passwd.equals("")&&!name.equals("")){
                isState = addInformation(id,passwd,name)
                if(isState){
                    toast("회원가입이 완료되어 홈화면으로 이동합니다..")
                    val timer = timer(period=2000){
                        finish()
                    }
                }
            }

            else
                toast("회원가입정보를 다시 한번 확인해주세요.")


        }

    }
    fun addInformation(id:String, pw:String, name:String):Boolean{

        val preference_id = Preference_id(applicationContext,id)
        val preference_name = Preference_name(applicationContext,name)
        preference_id.setString(id,id)
        preference_id.setString("password",pw)
        preference_id.setString("name",name)
        preference_name.setString(name,id)



        return true
    }
}