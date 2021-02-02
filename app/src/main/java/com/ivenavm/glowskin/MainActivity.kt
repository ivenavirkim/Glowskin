package com.ivenavm.glowskin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    lateinit var userDBHelper: DBHelper
    lateinit var inputnama: EditText
    lateinit var inputno: EditText
    lateinit var inputkeluhan: EditText
    lateinit var inputtreatment: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inputnama = findViewById(R.id.input_nama)
        inputno = findViewById(R.id.input_no)
        inputkeluhan = findViewById(R.id.input_keluhan)
        inputtreatment = findViewById(R.id.input_treatment)
        userDBHelper = DBHelper(this)
    }
    fun addData(v: View){
        var nama = inputnama.text.toString()
        var no = inputno.text.toString()
        var keluhan = inputkeluhan.text.toString()
        var treatment = inputtreatment.text.toString()
        userDBHelper.glosuser(nama, no, keluhan, treatment)
        inputnama.setText("")
        inputno.setText("")
        inputkeluhan.setText("")
        inputtreatment.setText("")

    }
    fun showAll(v: View){
        var pindah = Intent(this, RvDbActivity::class.java)
        startActivity(pindah)
    }

    fun LogOut(v: View){

        val savedLogin = getSharedPreferences("Login", MODE_PRIVATE)
        val editSavedLogin = savedLogin.edit()
        editSavedLogin.putString("Email", null)
        editSavedLogin.putString("Password", null)
        editSavedLogin.putString("Status", "Off")
        editSavedLogin.commit()
        startActivity(Intent(this, LoginActivity::class.java))
    }



}