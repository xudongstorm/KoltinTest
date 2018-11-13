package com.example.koltintest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class MainActivity : AppCompatActivity(),View.OnClickListener,View.OnLongClickListener {
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.bt_1 ->{
                network();
            }
            R.id.bt_2 ->{
                Toast.makeText(this,"按钮2",Toast.LENGTH_SHORT).show();
                gson();
            }
            R.id.bt_3 ->{
                Toast.makeText(this,"按钮3",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private fun gson() {
        val string="http://www.wanandroid.com/tools/mockapi/2872/student"
        doAsync {
            val fore = URL(string).readText()
            val fromGson = Gson().fromJson(fore,Student::class.java)
            this.uiThread {
                txt_1.text = "学生的名字是${fromGson.name},年龄是${fromGson.age}"
            }
        }
    }

    private fun network() {
        val string="http://www.wanandroid.com/tools/mockapi/2872/student"
        doAsync {
            val fore = URL(string).readText()
            Log.d("tonjies", fore)
        }
    }

    override fun onLongClick(v: View?): Boolean {
        Toast.makeText(this, "长按事件", Toast.LENGTH_LONG).show()
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txt_1.text="你好，Koltin"
        init()
    }

    private fun init(){
        bt_1.setOnClickListener(this)
        bt_2.setOnClickListener(this)
        bt_3.setOnClickListener(this)
        bt_1.setOnLongClickListener(this)
        bt_2.setOnLongClickListener(this)
        bt_3.setOnLongClickListener(this)
    }
}

