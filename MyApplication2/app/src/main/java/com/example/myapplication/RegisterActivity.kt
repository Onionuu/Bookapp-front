package com.example.myapplication

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        register_cityInput.setOnClickListener {
            createDialog("城市")
        }
        register_collegeInput.setOnClickListener {
            createDialog("学校")
        }
    }

    private fun createDialog(s:String){
        val arrayCity = arrayOf("广东广州","广东深圳")
        val arrayCollege = arrayOf("华南师范大学")
        AlertDialog.Builder(this).apply {
            setTitle("请选择$s")
            if (s=="城市"){
            setItems(arrayCity, DialogInterface.OnClickListener { dialog, which ->
                register_cityInput.text = arrayCity[which]
                 })
            }else if (s=="学校"){
                setItems(arrayCollege, DialogInterface.OnClickListener { dialog, which ->
                    register_collegeInput.text = arrayCollege[which]
                })
            }else   {
                setMessage("加载出错")
            }
            show()
        }
    }


}