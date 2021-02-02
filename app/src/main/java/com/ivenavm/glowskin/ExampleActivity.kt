package com.ivenavm.glowskin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView

class ExampleActivity : AppCompatActivity() {
    lateinit var userDBHelper: DBHelper

    lateinit var tampil: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
        userDBHelper = DBHelper(this)
        tampil = findViewById(R.id.tampilsemua)
        var tampilkan = userDBHelper.fullDataUser()
        tampilkan.forEach {
            tampil.text = tampil.text.toString() + " " + it.nama.toString() + " - "  + it.no.toString() + " - "  +it.keluh.toString() + " - "  + it.treat.toString() + "\n"
        }
    }
}