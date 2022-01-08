package com.unal.crud_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unal.crud_sqlite.DB.CompanyModel
import com.unal.crud_sqlite.DB.SQLiteHelper
import java.util.stream.DoubleStream.builder

class MainActivity : AppCompatActivity() {

    private lateinit var edName:EditText
    private lateinit var edEmail:EditText
    private lateinit var edURL:EditText
    private lateinit var edPhone:EditText
    private lateinit var edProducts:EditText
    private lateinit var edClasification:Spinner

    private lateinit var btnAdd: Button
    private lateinit var btnUpdate: Button

    private lateinit var sqLiteHelper: SQLiteHelper
    private var recyclerView: RecyclerView? = null
    private var adapter: CompanyAdapter? = null
    var layoutmanager: RecyclerView.LayoutManager? = null

    private var row: CompanyModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        sqLiteHelper = SQLiteHelper(this)
        viewList()
        btnAdd.setOnClickListener { addRow() }
        btnUpdate.setOnClickListener { updateRow() }


        adapter?.setOnClickItem{
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
            row =it
            edEmail.setText(it.email)
            edName.setText(it.name)
            edURL.setText(it.URL)
            var classi=0
            if(it.classification.equals("Consultoría")){
                classi=1
            }else if(it.classification.equals("Desarrollo a la medida")){
                classi=2
            }else if(it.classification.equals("Fábrica de software")){
                classi=3
            }

            edClasification.setSelection(classi)
            edProducts.setText(it.products)
            edPhone.setText(it.phone)
        }

        adapter?.setOnClickDeleteItem{
            deleteRow(it.id)
        }

        btnUpdate.setOnClickListener {
            updateRow()
        }

    }

    private fun deleteRow(id:Int){
        if(id==null) return
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Seguro de Borrar?")
        builder.setCancelable(true)
        builder.setPositiveButton("si"){dialog, _ ->
            sqLiteHelper.deleteRowById(id)
            viewList()
            dialog.dismiss()
        }
        builder.setNegativeButton("No"){dialog, _ ->
            dialog.dismiss()
        }

        val alert = builder.create()

        alert.show()

    }

    private fun updateRow(){

        val name = edName.text.toString()
        val email = edEmail.text.toString()
        val phone = edPhone.text.toString()
        val URL = edURL.text.toString()
        val products = edProducts.text.toString()
        val clasification = edClasification!!.getSelectedItem().toString()

        if (name == row?.name && email == row?.email && phone == row?.phone && URL == row?.URL && products == row?.products && clasification == row?.classification){
            Toast.makeText(this, "No hay cambios", Toast.LENGTH_SHORT).show()
            return
        }

        if(row==null) return

        val row = CompanyModel(id=row!!.id, name = name,email = email, URL = URL, phone = phone, products = products, classification = clasification)
        val status = sqLiteHelper.updateRow(row)
        if(status> -1){
           Toast.makeText(this, "Actualizado",Toast.LENGTH_SHORT).show()
           clearEditText()
           viewList()
        }else{
           Toast.makeText(this, "Error Actualizando",Toast.LENGTH_SHORT).show()
        }


    }

    private fun viewList() {
        val rowList = sqLiteHelper.getAllRows()
        Log.e("List","${rowList}")
        initRecyclerView(rowList)
    }

    private fun addRow(){
        val name = edName.text.toString()
        val email = edEmail.text.toString()
        val phone = edPhone.text.toString()
        val URL = edURL.text.toString()
        val products = edProducts.text.toString()
        val clasification = edClasification!!.getSelectedItem().toString()

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || URL.isEmpty() || products.isEmpty() || clasification.isEmpty()){
            Toast.makeText(this, "Llena todos los espacios", Toast.LENGTH_SHORT).show()
        }else{
            val row = CompanyModel( name = name,email = email, URL = URL, phone = phone, products = products, classification = clasification)
            val status = sqLiteHelper.insertRow(row)

            if(status> -1){
                Toast.makeText(this, "Agregado",Toast.LENGTH_SHORT).show()
                clearEditText()
                viewList()
            }else{
                Toast.makeText(this, "Error Agregando",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clearEditText() {
        edEmail.setText("")
        edName.setText("")
        edURL.setText("")
        edPhone.setText("")
        edProducts.setText("")
        edClasification.setSelection(0)
        edName.requestFocus()
    }

    private fun initRecyclerView(items: ArrayList<CompanyModel>){
        adapter = CompanyAdapter(items)
        layoutmanager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = layoutmanager
        recyclerView?.adapter = adapter
    }

    private fun initView() {
        edName = findViewById(R.id.txt_name)
        edEmail = findViewById(R.id.txt_email)
        edURL = findViewById(R.id.txt_URL)
        edPhone = findViewById(R.id.txt_phone)
        edProducts = findViewById(R.id.txt_products)
        edClasification = findViewById(R.id.spin_clasification)


        btnAdd = findViewById(R.id.btn_create)
        btnUpdate= findViewById(R.id.btn_update)
        recyclerView = findViewById(R.id.recycler_vertical)
    }
}