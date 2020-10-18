package com.example.mobileprogramming_homework2

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.graphics.drawable.AnimationDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast


class LoginActivity : AppCompatActivity() {
    private lateinit var prefe : Preference_id
    private lateinit var name:String
    private lateinit var action:AnimationDrawable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var intent: Intent


        setTitle("로그인")


        /*action = action_image.background as AnimationDrawable
        action.start()*/

        action = action_image.drawable as AnimationDrawable
        action.start()

        loging_Btn.setOnClickListener{//로그인 버튼
            val id = id_textView.text.toString()
            val pass = password_texView.text.toString()

            val bool_login = search_inform(id,pass)

            if(bool_login){
                intent = Intent(this,HomeActivity::class.java)
                intent.putExtra("name",name)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun search_inform(id: String, passs: String):Boolean {



        var bool:Boolean = false
        prefe = Preference_id(applicationContext,id)
        val pre_id = prefe.getString(id)//파일에 저장된 id값을 가져온다.
        val pre_passwd = prefe.getString("password")
        name = prefe.getString("name")
        Log.d("TAG", "pre_id: "+pre_id)
        Log.d("TAG", "pre_passwd: "+pre_passwd)
        Log.d("TAG", "id: "+id)
        Log.d("TAG", "passs: "+passs)
        if(pre_id.equals(id)&&pre_passwd.equals(passs)){ //아이디 비밀번호가 모두 같을 경우
            if(pre_id.equals("")&&pre_passwd.equals("")){
                toast("아이디와 비밀번호를 확인해보세요.")
            }else{
                toast("정상적으로 로그인이 완료되었습니다.")
                bool = true
            }

        }else{
            if(!pre_id.equals(id)&&pre_passwd.equals(passs))//아이디 ,비밀번호 모두 같지 않은경우
                toast("아이디와 비밀번호를 다시 입력하세요.")
            else if(!pre_id.equals(id))
                toast("아이디를 다시 입력하세요.")
            else
                toast("비밀번호를 다시 입력하세요.")
        }
        return bool;
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
        menuInflater.inflate(R.menu.loginmenu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent:Intent
        when(item.itemId){
            R.id.singup_Btn->{//회원가입
                id_textView.setText("")
                password_texView.setText("")
                intent = Intent(this,SingupActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.find_id_passwd->{//아이디 비밀번호 찾기
                intent = Intent(this,FindlicenseActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.call_Btn->{//관리자에게 전화하기
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
}