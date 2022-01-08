package com.unal.crud_sqlite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.unal.crud_sqlite.DB.CompanyModel

class CompanyAdapter (companyArrayList : ArrayList<CompanyModel>): RecyclerView.Adapter<CompanyAdapter.ViewHolder>() {
    private var companyList : ArrayList<CompanyModel>? = null
    private var onClickItem: ((CompanyModel)->Unit)? =null
    private var onClickDeleteItem: ((CompanyModel)->Unit)? =null
    init{
        this.companyList = companyArrayList
        notifyDataSetChanged()
    }

    fun setOnClickItem(callback: (CompanyModel) ->Unit){
        this.onClickItem = callback
    }
    fun setOnClickDeleteItem(callback: (CompanyModel) ->Unit){
        this.onClickDeleteItem = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)//create instance context
        var Holder = ViewHolder(vista)
        return Holder
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val row = companyList?.get(position)

        holder.id?.text= row!!.id.toString()
        holder.name?.text = row!!.name
        holder.email?.text = row!!.email
        holder.url?.text = row!!.URL
        holder.phone?.text = row!!.phone
        holder.products?.text = row!!.products
        holder.clasification?.text = row!!.classification

        holder.itemView.setOnClickListener{
            onClickItem?.invoke(row)
        }
        holder.btn_delete.setOnClickListener {
            onClickDeleteItem?.invoke(row)
        }
    }

    override fun getItemCount(): Int {
        return companyList!!.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var id = view.findViewById<TextView>(R.id.txt_Id)
        var name = view.findViewById<TextView>(R.id.txt_Name)
        var email = view.findViewById<TextView>(R.id.txt_MAIL)
        var url = view.findViewById<TextView>(R.id.txt_URL)
        var phone = view.findViewById<TextView>(R.id.txt_PHONE)
        var products = view.findViewById<TextView>(R.id.txt_PRODUCTS)
        var clasification = view.findViewById<TextView>(R.id.txt_CLASIFICATION)


        var btn_delete = view.findViewById<Button>(R.id.btn_delete)


        init {
            id = view.findViewById(R.id.txt_Id)
            name = view.findViewById(R.id.txt_Name)
            email = view.findViewById(R.id.txt_MAIL)
            url = view.findViewById(R.id.txt_URL)
            phone = view.findViewById(R.id.txt_PHONE)
            products = view.findViewById(R.id.txt_PRODUCTS)
            clasification = view.findViewById(R.id.txt_CLASIFICATION)

            btn_delete = view.findViewById(R.id.btn_delete)
        }

    }
}