package com.ivenavm.glowskin

import android.provider.BaseColumns

object DBInfo {
    class UserTable: BaseColumns {
        companion object {
            val TABLE_NAME = "user"
            val COL_EMAIL = "email"
            val COL_PASS = "pass"
            val COL_FULLNAME = "fullname"
            val COL_ALAMAT = "alamat"
            val COL_JENKAL = "jeniskelamin"
            val COL_JUMLAH = "jumlah"
            val GLOS = "glowskin"
            val COL_NAMA = "namalengkap"
            val COL_NO = "nohp"
            val COL_KEL = "keluhan"
            val COL_TREAT = "treatment"
        }
    }
}