package com.ivenavm.glowskin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class UpdateActivity : AppCompatActivity() {
    lateinit var inputnl: EditText
    lateinit var inputnohp: EditText
    lateinit var inputklh: EditText
    lateinit var inputtrt: EditText
    lateinit var btnupdate: Button
    lateinit var btncancel: Button
    lateinit var userDBHelper: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        inputnl = findViewById(R.id.input_nl)
        inputnohp = findViewById(R.id.input_nohp)
        inputklh= findViewById(R.id.input_klh)
        inputtrt = findViewById(R.id.input_trt)
        userDBHelper = DBHelper(this)
        btnupdate = findViewById(R.id.btn_update)
        btncancel = findViewById(R.id.btn_cancel)
        userDBHelper = DBHelper(this)
        val bundle = intent.extras
        if (bundle!=null){
            inputnl.setText(bundle.getString("nama"))
            inputnohp.setText(bundle.getString("no"))
            inputklh.setText(bundle.getString("keluhan"))
            inputtrt.setText(bundle.getString("treatment"))
        }
    }
    fun updateData(v: View){
        var inl = inputnl.text.toString()
        var ihp = inputnohp.text.toString()
        var iklh = inputklh.text.toString()
        var itrt = inputtrt.text.toString()
        userDBHelper.updateData(inl,ihp, iklh, itrt)
        var pindah = Intent(this, RvDbActivity::class.java)
        startActivity(pindah)
    }
    fun cancelData(v: View){
        var pindah = Intent(this, RvDbActivity::class.java)
        startActivity(pindah)
    }
}