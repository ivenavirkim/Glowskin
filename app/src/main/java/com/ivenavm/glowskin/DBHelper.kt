package com.ivenavm.glowskin

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        val DATABASE_NAME = "myaps.db"
        val DATABASE_VERSION = 1
        private val SQL_CREATE_USER = "CREATE TABLE " + DBInfo.UserTable.TABLE_NAME + "("+DBInfo.UserTable.COL_EMAIL+" VARCHAR(200) PRIMARY KEY, " + DBInfo.UserTable.COL_PASS + " TEXT, " + DBInfo.UserTable.COL_FULLNAME + " TEXT, " + DBInfo.UserTable.COL_JENKAL + " VARCHAR(200), " + DBInfo.UserTable.COL_ALAMAT + " TEXT)"
        private val SQL_CREATE_GLOS = "CREATE TABLE " + DBInfo.UserTable.GLOS + "("+DBInfo.UserTable.COL_NAMA+" VARCHAR(200), " + DBInfo.UserTable.COL_NO + " VARCHAR(255) PRIMARY KEY, " + DBInfo.UserTable.COL_KEL + " VARCHAR(255), " + DBInfo.UserTable.COL_TREAT + " VARCHAR(200))"
        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBInfo.UserTable.TABLE_NAME

    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_USER)
        db?.execSQL(SQL_CREATE_GLOS)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    @Throws(SQLiteConstraintException::class)
    fun RegisterUser(emailin: String, passin:String, fullnamein: String, jenkalin: String, alamatin: String) {
        val db = writableDatabase
        val namatable = DBInfo.UserTable.TABLE_NAME
        val emailt = DBInfo.UserTable.COL_EMAIL
        val passt = DBInfo.UserTable.COL_PASS
        val fullnamet = DBInfo.UserTable.COL_FULLNAME
        val jenkalt = DBInfo.UserTable.COL_JENKAL
        val alamatt = DBInfo.UserTable.COL_ALAMAT
        var sql = "INSERT INTO " + namatable + " (" + emailt + ", " + passt+ ", " + fullnamet + ", " + jenkalt + ", " + alamatt + ") VALUES('" + emailin+ "', '" + passin + "', '" + fullnamein + "', '" + jenkalin + "', '" + alamatin+ "')"
        db.execSQL(sql)
    }
    @Throws(SQLiteConstraintException::class)
    fun glosuser(nama: String, no:String, keluh: String, treatment: String) {
        val db = writableDatabase
        val gloskin = DBInfo.UserTable.GLOS
        val namas = DBInfo.UserTable.COL_NAMA
        val nos = DBInfo.UserTable.COL_NO
        val keluhs = DBInfo.UserTable.COL_KEL
        val treatments = DBInfo.UserTable.COL_TREAT
        var sql = "INSERT INTO " + gloskin + " (" + namas + ", " + nos + ", " + keluhs + ", " + treatments + ") VALUES('" + nama+ "', '" + no + "', '" + keluh + "', '" + treatment + "')"
        db.execSQL(sql)
    }
    fun cekUser(emailin: String): String {
        val db = writableDatabase
        var cursor: Cursor? = null
        var jumlah = ""
        try {
            cursor = db.rawQuery("SELECT COUNT("+ DBInfo.UserTable.COL_EMAIL +") as jumlah FROM "+ DBInfo.UserTable.TABLE_NAME + " WHERE " + DBInfo.UserTable.COL_EMAIL + "=='" + emailin +"'" , null)
        } catch (e: android.database.SQLException) {
            db.execSQL(SQL_CREATE_USER)
            return "Tabel Dibuat"
        }
        if (cursor!!.moveToFirst()){
            jumlah = cursor.getString(cursor.getColumnIndex(DBInfo.UserTable.COL_JUMLAH))
        }
        return jumlah
    }
    fun cekLogin(emailin: String, passin: String): String {
        val db = writableDatabase
        var cursor: Cursor? = null
        var jumlah = ""
        try {
            cursor = db.rawQuery("SELECT COUNT("+ DBInfo.UserTable.COL_EMAIL +") as jumlah FROM "+ DBInfo.UserTable.TABLE_NAME + " WHERE " + DBInfo.UserTable.COL_EMAIL + "=='" + emailin +"' AND " + DBInfo.UserTable.COL_PASS + "=='" + passin + "'" , null)
        } catch (e: android.database.SQLException) {
            db.execSQL(SQL_CREATE_USER)
            return "Tabel Dibuat"
        }
        if (cursor!!.moveToFirst()){
            jumlah = cursor.getString(cursor.getColumnIndex(DBInfo.UserTable.COL_JUMLAH))
        }
        return jumlah
    }
    fun fullDataUser():ArrayList<DataModelGloskin> {
        val users = arrayListOf<DataModelGloskin>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("SELECT * FROM "+DBInfo.UserTable.GLOS, null)
        } catch (e: android.database.SQLException) {
            db.execSQL(SQL_CREATE_GLOS)
            return ArrayList()
        }
        var nama: String
        var no: String
        var keluh: String
        var treatment: String
        if (cursor!!.moveToFirst()){
            while (cursor.isAfterLast==false){
                nama = cursor.getString(cursor.getColumnIndex(DBInfo.UserTable.COL_NAMA))
                no = cursor.getString(cursor.getColumnIndex(DBInfo.UserTable.COL_NO))
                keluh = cursor.getString(cursor.getColumnIndex(DBInfo.UserTable.COL_KEL))
                treatment = cursor.getString(cursor.getColumnIndex(DBInfo.UserTable.COL_TREAT))

                users.add(DataModelGloskin(nama, no, keluh, treatment))
                cursor.moveToNext()
            }
        }
        return  users
    }
    fun deleteData(nomorin: String){
        val db = writableDatabase
        val namatablet = DBInfo.UserTable.GLOS
        val nomor = DBInfo.UserTable.COL_NO
        val sql = "DELETE FROM " +namatablet+ " WHERE "+nomor+"='"+nomorin+"'"
        db.execSQL(sql)
    }
    fun updateData(namain: String, noin: String, keluhin: String, treatin: String){
        val db = writableDatabase
        val namatablet = DBInfo.UserTable.GLOS
        val nomor = DBInfo.UserTable.COL_NO
        val nale = DBInfo.UserTable.COL_NAMA
        val kel = DBInfo.UserTable.COL_KEL
        val tre = DBInfo.UserTable.COL_TREAT
        var sql = "UPDATE "+ namatablet + " SET "+
                kel+"='"+namain+"', "+tre+"='"+noin+"', "+nale+"='"+keluhin+"' "+
                "WHERE "+nomor+"='"+treatin+"'"
        db.execSQL(sql)
    }
}