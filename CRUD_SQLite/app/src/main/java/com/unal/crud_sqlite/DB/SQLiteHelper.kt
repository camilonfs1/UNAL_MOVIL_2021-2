package com.unal.crud_sqlite.DB

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.ArrayAdapter
import java.lang.Exception
import kotlin.math.E

class SQLiteHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "company"
        private const val TBL_NAME = "tbl_company"
        private const val ID = "id"
        private const val NAME = "name"
        private const val EMAIL = "email"
        private const val PHONE = "phone"
        private const val URL = "url"
        private const val PRODUCTS = "products"
        private const val CLASIFICATION = "clasification"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblCompany = (
                "CREATE TABLE " + TBL_NAME + "("
                        + ID + " INTEGER PRIMARY KEY,"
                        + NAME + " TEXT,"
                        + PHONE + " TEXT,"
                        + URL + " TEXT,"
                        + PRODUCTS + " TEXT,"
                        + CLASIFICATION + " TEXT,"
                        + EMAIL + " TEXT" + ")")
        db?.execSQL(createTblCompany)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_NAME")
        onCreate(db)
    }

    fun insertRow(row: CompanyModel): Long {
        val db = this.writableDatabase

        val contenValues = ContentValues()
        contenValues.put(ID, row.id)
        contenValues.put(NAME, row.name)
        contenValues.put(EMAIL, row.email)
        contenValues.put(PHONE, row.phone)
        contenValues.put(URL, row.URL)
        contenValues.put(PRODUCTS, row.products)
        contenValues.put(CLASIFICATION, row.classification)

        val success = db.insert(TBL_NAME, null, contenValues)
        db.close()
        return success
    }

    @SuppressLint("Range")
    fun getAllRows(): ArrayList<CompanyModel> {
        val rowList: ArrayList<CompanyModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_NAME"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var name: String
        var email: String
        var url: String
        var phone: String
        var products: String
        var clasification: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                name = cursor.getString(cursor.getColumnIndex("name"))
                email = cursor.getString(cursor.getColumnIndex("email"))
                url = cursor.getString(cursor.getColumnIndex("url"))
                phone = cursor.getString(cursor.getColumnIndex("phone"))
                products = cursor.getString(cursor.getColumnIndex("products"))
                clasification = cursor.getString(cursor.getColumnIndex("clasification"))

                val row = CompanyModel(id, name, email,phone,url,products,clasification)
                rowList.add(row)
            } while (cursor.moveToNext())
        }
        return rowList
    }

    fun updateRow(row: CompanyModel): Int{

        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, row.id)
        contentValues.put(NAME, row.name)
        contentValues.put(EMAIL, row.email)
        contentValues.put(URL, row.URL)
        contentValues.put(PRODUCTS, row.products)
        contentValues.put(PHONE, row.phone)
        contentValues.put(CLASIFICATION, row.classification)

        val success= db.update(TBL_NAME, contentValues, "id="+row.id,null)
        db.close()
        return success
    }

    fun deleteRowById(Id: Int): Int{
        val db = this.writableDatabase
        val success= db.delete(TBL_NAME,  "id="+Id,null)
        db.close()
        return success
    }

}